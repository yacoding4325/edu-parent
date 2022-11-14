<template>
    <div class="app-container">
        <el-steps :active="1" finish-status="success">
          <el-step title="填写课程基本信息"></el-step>
          <el-step title="创建课程大纲"></el-step>
          <el-step title="提交审核"></el-step>
        </el-steps>
        <el-form label-width="120px">
          <el-form-item label="课程标题">
            <el-input v-model="courseInfo.title" placeholder="示例：Java基础课：从0入门，学完可掌握基础知识"/>
          </el-form-item>

          <el-form-item label="课程类别">
            <el-select v-model="courseInfo.subjectParentId" placeholder="一级分类" @change="subjectLevelOneChanged">
              <el-option v-for="subject in subjectNestedList" :key="subject.id" :label="subject.title" :value="subject.id"></el-option>
            </el-select>
            <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
              <el-option v-for="subject in subSubjectList" :key="subject.id" :label="subject.title" :value="subject.id"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="课程讲师">
            <el-select v-model="courseInfo.teacherId" placeholder="请选择">
              <el-option v-for="teacher in teacherList" :key="teacher.id" :label="teacher.name" :value="teacher.id"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="总课时">
            <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
          </el-form-item>
          <!-- 课程简介 -->
          <el-form-item label="课程简介">
            <tinymce :height="300" v-model="courseInfo.description" />
          </el-form-item>

          <!-- 课程封面图片上传-->
          <el-form-item label="课程封面">
            <el-upload 
                :action="BASE_API+'/admin/oss/file/upload?module=cover'"
                :show-file-list="false"
                :on-success="handleCoverSuccess"
                :on-error="handleCoverError"
                :before-upload="beforeCoverUpload"
                class="cover-uploader">
              <img :src="courseInfo.cover">
            </el-upload>
          </el-form-item>

          <el-form-item label="课程价格">
            <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/>元
          </el-form-item>

          <el-form-item>
            <el-button style="margin-top: 12px;" type="primary" :disabled="saveBtnDisabled" @click="saveOrUpdate">保存并下一步</el-button>
          </el-form-item>
        </el-form>
        
    </div>
</template>
<script>
import course from '@/api/course'
import teacher from '@/api/teacher'
import subject from '@/api/subject'
import Tinymce from '@/components/Tinymce'

const defaultForm = {
  title: '',  //标题
  subjectId: '',  //课程分类
  teacherId: '',  //讲师
  lessonNum: 0,   //总课时
  description: '',  //课程简介
  cover: '/static/default.jpg',      //课程封面图片路径
  price: 0        //课程销售价格，设置为0则可免费观看
}

  export default {
    components: {
      Tinymce
    },
    data() {
      return {
        courseInfo: defaultForm,
        saveBtnDisabled: false,    //保存按钮是否禁用
        teacherList: [],            //所有讲师
        subjectNestedList: [],      //一级分类列表
        subSubjectList: [],          //二级分类列表
        BASE_API: process.env.BASE_API
      }
    },

    created() {
      this.init()
    },

    watch: {
      $route(to,form){
        this.init()
      }
    },

    methods: {
      init() {
        //获取讲师列表
        this.initTeacherList()        
        if(this.$route.params && this.$route.params.id){
          const id = this.$route.params.id
          this.fetchCourseInfoById(id)
        }else{
          //获取一级分类
          this.initSubjectList()
          this.courseInfo = {...defaultForm}
        }
      },

      //根据主键id获取课程信息
      fetchCourseInfoById(id) {
        course.getCourseInfoById(id).then(responseCourse => {
          this.courseInfo = responseCourse.data.item
          subject.getNestedTreeList().then(responseSubject => {
            this.subjectNestedList = responseSubject.data.items
             //判断this.subjectNestedList下哪个一级类别是当前绑定的一级类别
            for(let i=0;i<this.subjectNestedList.length;i++){
              if(this.subjectNestedList[i].id === this.courseInfo.subjectParentId){
                //找到对应一级类别的二级列表
                this.subSubjectList = this.subjectNestedList[i].children
              }
            }
          })         
        })
      },

      //保存或更新
      saveOrUpdate() {
        this.saveBtnDisabled = true
        if(this.courseInfo.id){
          this.updateData()
        }else{
          this.saveData()
        }
      },

      //保存
      saveData(){
        course.saveCourseInfo(this.courseInfo).then(response => {
          this.$message({
            type: 'success',
            message: '保存成功！'
          })
          this.$router.push({path: '/course/chapter/' + response.data.courseId})
        })
      },

      //更新
      updateData(){
        course.updateCourseInfo(this.courseInfo).then(response => {
          this.$message({
            type: 'success',
            message: '修改成功！'
          })
          this.$router.push({path: '/course/chapter/' + this.courseInfo.id})
        })        
      },

      //获取所有讲师
      initTeacherList() {
        teacher.getList().then(response => {
          this.teacherList = response.data.items
        })
      },

      //获取一级分类
      initSubjectList(){
        subject.getNestedTreeList().then(response => {
          this.subjectNestedList = response.data.items
        })
      },

      //根据一级分类id获取二级分类列表
      subjectLevelOneChanged(value){
        for(let i=0;i<this.subjectNestedList.length;i++){
          if(this.subjectNestedList[i].id === value){
            this.subSubjectList = this.subjectNestedList[i].children
            this.courseInfo.subjectId = ''
          }
        }
      },

      // 上传成功的钩子
      handleCoverSuccess(response) {
          if(response.success) {
              this.$message.success(response.message)
              this.courseInfo.cover = response.data.url
              this.$forceUpdate()
          }else{
              this.$message.error(response.message)
          }
      },
      //上传失败的钩子
      handleCoverError() {
          this.$message.error(response.message)
      },
      //文件上传之前的钩子，可以用语校验
      beforeCoverUpload(file) {
          let isJpg = file.type === 'image/jpeg'            
          if(!isJpg){
              this.$message.error('上传头像图片只能是JPG格式！')
              return false
          }
          let isLt2M = file.size/1024/1024 < 2
          if(!isLt2M){
              this.$message.error('上传头像图片不能超过2MB！')
              return false
          }
          return true
      }
    }
  }
</script>

<style>
.tinymce-container {
  line-height: 29px;
}
.cover-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.cover-uploader .el-upload:hover {
  border-color: #409EFF;
}

.cover-uploader .cover-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 640px;
  height: 357px;
  line-height: 178px;
  text-align: center;
}

.cover-uploader img {
  width: 640px;
  height: 357px;
  display: block;
}
</style>