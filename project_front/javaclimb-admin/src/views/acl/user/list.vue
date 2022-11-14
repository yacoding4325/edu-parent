<template>
    <div class="app-container">
        <!-- 查询条件 -->
        <el-form :inline="true">
          <el-form-item>
            <el-input v-model="searchObj.username" placeholder="用户名"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
            <el-button type="default" @click="resetData()">清空</el-button>
          </el-form-item>
        </el-form>
        <div style="margin-bottom:10px;">
            <el-button type="danger" size="mini" @click="addUser()">添加</el-button>
        </div>
        <!-- 表格 -->
        <el-table
            :data="list"
            border
            stripe
            @selection-change="handleSelectionChange">
            <el-table-column type="selection"/>
            <el-table-column label="ID" width="50">
                <!-- 使用连续的序号 -->
                <template slot-scope="scope">
                {{ (currentPage - 1) * limit + scope.$index + 1 }}
                </template>
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="150"/>
            <el-table-column prop="nickName" label="用户昵称"/>
            <el-table-column prop="gmtCreate" label="创建时间" width="180"/>
            <el-table-column label="操作" width="250">
                <template slot-scope="scope">
                    <router-link :to="'/acl/user/role/'+scope.row.id">
                        <el-button type="info" size="mini" icon="el-icon-info"></el-button>
                    </router-link>
                    <router-link :to="'/acl/user/update/'+scope.row.id">
                        <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
                    </router-link>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeById(scope.row)"></el-button>     
                </template>
            </el-table-column>
        </el-table>
        <!--分页组件-->
        <el-pagination
            :current-page="currentPage"
            :page-size="limit"
            :total="total"
            :page-sizes="[5, 10, 15, 20]"
            style="padding: 12px 8px; text-align: center"
            layout="sizes, prev, pager, next, jumper, ->, total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>
    </div>
</template>
<script>
import userApi from '@/api/acl/user'

export default {
    data() {            //定义数据
        return {
            list: [],   // 列表数据
            total: 0,   // 总记录数
            currentPage: 1, //当前页
            limit: 10,      //每页记录数
            searchObj: {},   //查询用到的对象
            multipleSelection: []   //选中的记录列表
        }
    },
    created(){          //当页面加载时获取数据
        this.fetchData();
    },
    methods: {
        fetchData(){         //调用api里面的函数  
            userApi.pageList(this.currentPage,this.limit,this.searchObj).then(resp => {
                this.list = resp.data.rows
                this.total = resp.data.total     
            })
        },
        //改变每页条数
        handleSizeChange(size){
            this.limit = size;
            this.fetchData();
        },
        // 改变页码
        handleCurrentChange(page){
            this.currentPage = page;
            this.fetchData();
        },
        //清空查询条件
        resetData(){
            this.searchObj = {};
            this.fetchData();
        },
        // 删除单个用户
        removeById(data){
            this.$confirm('此操作将永久删除'+data.username+', 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    //调用删除的api
                    userApi.removeById(data.id).then(resp => {
                        this.fetchData();
                        this.$message({
                            type: 'success',
                            message: resp.message
                        });
                    })
                    
                });
        },
        //多选点击事件
        handleSelectionChange(selection){
            this.multipleSelection = selection
        },
        
        //去添加用户页面
        addUser() {
            this.$router.push({path:`/acl/user/form`})
        }
    }
}
</script>
