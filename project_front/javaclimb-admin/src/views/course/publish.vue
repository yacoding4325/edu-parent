<template>
    <div class="app-container">
        <el-steps :active="3" finish-status="success">
            <el-step title="填写课程基本信息"></el-step>
            <el-step title="创建课程大纲"></el-step>
            <el-step title="提交审核"></el-step>
        </el-steps>
        <div class="ccInfo">
          <img :src="coursePublish.cover">
          <div class="main">
            <h2>{{coursePublish.title}}</h2>
            <p class="gray"><span>共{{coursePublish.lessonNum}}课时</span></p>
            <p><span>所属分类：{{coursePublish.subjectLevelOne}} - {{coursePublish.subjectLevelTwo}}</span></p>
            <p>课程讲师：{{coursePublish.teacherName}}</p>
            <h3 class="red">￥{{coursePublish.price}}</h3>
          </div>
        </div>

        <el-button style="margin-top: 12px;" @click="previous">返回修改</el-button>
        <el-button style="margin-top: 12px;" type="primary" @click="publish">发布课程</el-button>
    </div>
</template>
<script>
  import publish from '@/api/publish'

  export default {
    data() {
      return {
        saveBtnDisabled: false,     //保存按钮是否禁用
        courseId: '',               //所属课程id
        coursePublish: {}           //课程发布信息
      };
    },

    created(){
      this.init()
    },

    methods: {
      init(){
        if(this.$route.params && this.$route.params.id){
          this.courseId = this.$route.params.id
          this.fetchCoursePublishInfoByCourseId()
        }
      },

      fetchCoursePublishInfoByCourseId(){
        publish.selectCoursePublishVoById(this.courseId).then(response => {
          this.coursePublish = response.data.item
        })
      },

      previous() {
        this.$router.push({path: '/course/chapter/' + this.courseId})
      },
      publish() {
        publish.publishCourse(this.courseId).then(response => {
          this.$router.push({path: '/course/list'})
        })        
      }
    }
  }
</script>

<style scoped>
  .ccInfo {
    background: #f5f5f5;
    padding: 20px;
    overflow: hidden;
    border: 1px dashed #DDD;
    margin-bottom: 40px;
    position: relative;
}
.ccInfo img {
    background: #d6d6d6;
    width: 500px;
    height: 278px;
    display: block;
    float: left;
    border: none;
}
.ccInfo .main {
    margin-left: 520px;
}
.ccInfo .main h2 {
    font-size: 28px;
    margin-bottom: 30px;
    line-height: 1;
    font-weight: normal;
}
.ccInfo .main p {
    margin-bottom: 10px;
    word-wrap: break-word;
    line-height: 24px;
    max-height: 48px;
    overflow: hidden;
}
.ccInfo .main p {
    margin-bottom: 10px;
    word-wrap: break-word;
    line-height: 24px;
    max-height: 48px;
    overflow: hidden;
}
.ccInfo .main h3 {
    left: 540px;
    bottom: 20px;
    line-height: 1;
    font-size: 28px;
    color: #d32f24;
    font-weight: normal;
    position: absolute;
}
</style>