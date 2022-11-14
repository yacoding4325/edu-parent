<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form :inline="true" class="demo-form-inline">
      <!-- 所属分类：级联下拉列表 -->
      <el-form-item label="课程类别">
        <el-select
          v-model="searchObj.subjectParentId"
          placeholder="一级分类"
          @change="subjectLevelOneChanged"
        >
          <el-option
            v-for="subject in subjectNestedList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          ></el-option>
        </el-select>
        <el-select v-model="searchObj.subjectId" placeholder="二级分类">
          <el-option
            v-for="subject in subSubjectList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <!-- 标题 -->
      <el-form-item>
        <el-input v-model="searchObj.title" placeholder="课程标题" />
      </el-form-item>
      <!-- 讲师列表 -->
      <el-form-item>
        <el-select v-model="searchObj.teacherId" placeholder="请选择讲师">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()"
        >查询</el-button
      >
      <el-button @click="resetData()">清空</el-button>
    </el-form>

    <!-- 查询结果表格 -->
    <el-table :data="list" border>
      <el-table-column label="#" width="50">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="封面" width="200" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.cover" alt="scope.row.title" width="100%" />
        </template>
      </el-table-column>
      <el-table-column label="课程信息">
        <template slot-scope="scope">
          <a href="">{{ scope.row.title }}</a>
          <p>
            分类：{{ scope.row.subjectParentTitle }} >
            {{ scope.row.subjectTitle }}
          </p>
          <p>
            课时：{{ scope.row.lessonNum }} 浏览：{{
              scope.row.viewCount
            }}
            付费学员：{{ scope.row.buyCount }}
          </p>
        </template>
      </el-table-column>
      <el-table-column label="讲师" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.teacherName }}
        </template>
      </el-table-column>
      <el-table-column label="价格" width="100" align="center">
        <template slot-scope="scope">
          <el-tag type="success" v-if="Number(scope.row.price) === 0"
            >免费</el-tag
          >
          <el-tag v-else>{{ scope.row.price }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="课程状态" width="100" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 'Draft' ? 'warning' : 'success'">
            {{ scope.row.status === "Draft" ? "未发布" : "已发布" }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="180" align="center">
        <template slot-scope="scope">
          {{ scope.row.gmtCreate }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/info/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改</el-button
            >
          </router-link>
          <router-link :to="'/course/chapter/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >编辑大纲</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeById(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      :page-sizes="[5, 10, 15, 20]"
      style="padding: 12px 8px; text-align: center"
      layout="sizes, prev, pager, next, jumper, ->, total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import course from "@/api/course";
import teacher from "@/api/teacher";
import subject from "@/api/subject";

export default {
  data() {
    return {
      //课程列表
      list: [],
      //总记录数
      total: 0,
      //第几页
      page: 1,
      //每页记录数
      limit: 5,
      //查询条件
      searchObj: {
        title: "", //课程名称
        teacherId: "", //讲师id
        subjectParentId: "", //一级类别id
        subjectId: "", //二级类别id
      },
      //讲师下拉框
      teacheList: [],
      //一级类别列表
      subjectNestedList: [],
      //二级类别列表
      subSubjectList: [],
    };
  },

  created() {
    this.initTeacherList();
    this.initSubjectList();
    this.fetchData();
  },

  methods: {
    //获取所有讲师
    initTeacherList() {
      teacher.getList().then((response) => {
        this.teacherList = response.data.items;
      });
    },

    //获取一级分类
    initSubjectList() {
      subject.getNestedTreeList().then((response) => {
        this.subjectNestedList = response.data.items;
      });
    },

    //根据一级分类id获取二级分类列表
    subjectLevelOneChanged(value) {
      for (let i = 0; i < this.subjectNestedList.length; i++) {
        if (this.subjectNestedList[i].id === value) {
          this.subSubjectList = this.subjectNestedList[i].children;
          this.courseInfo.subjectId = "";
        }
      }
    },

    //根据查询条件查询课程列表
    fetchData() {
      course.getPageList(this.page, this.limit, this.searchObj).then((resp) => {
        this.list = resp.data.rows;
        this.total = resp.data.total;
      });
    },

    //清空查询条件
    resetData() {
      this.searchObj = {};
      this.subSubjectList = [];
      this.page = 1;
      this.limit = 5;
      this.fetchData();
    },

    //删除一条记录
    removeById(data) {
        this.$confirm('此操作将永久删除'+data.title+'课程以及该课程下的章节和视频, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {
                //调用删除的api
                course.removeById(data.id).then(resp => {
                    this.fetchData();
                    this.$message({
                        type: 'success',
                        message: resp.message
                    });
                })

            });
    },
    //改变每页条数
    handleSizeChange(size) {
      this.limit = size;
      this.fetchData();
    },
    // 改变页码
    handleCurrentChange(page) {
      this.page = page;
      this.fetchData();
    }
  }
}
</script>