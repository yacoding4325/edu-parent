<template>
    <div class="app-container">
        <el-steps :active="2" finish-status="success">
            <el-step title="填写课程基本信息"></el-step>
            <el-step title="创建课程大纲"></el-step>
            <el-step title="提交审核"></el-step>
        </el-steps>
        <el-button type="text" @click="addChapter()">添加章节</el-button>
        <ul class="chapterList">
          <li v-for="chapter in chapterNestedList" :key="chapter.id">
            <p>
              {{chapter.title}}
              <span class="acts">
                <el-button type="text" @click="addVideo(chapter.id)">添加课时</el-button>
                <el-button type="text" @click="editChapter(chapter.id)">编辑</el-button>
                <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
              </span>
            </p>            
            <ul class="chapterList videoList">
              <li v-for="video in chapter.children" :key="video.id">
                <p>
                  {{video.title}}
                  <span class="acts">
                    <el-button type="text" @click="editVideo(video.id)">编辑</el-button>
                    <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
                  </span>
                </p>
              </li>
            </ul>
          </li>  
        </ul>
        <div>
          <el-button style="margin-top: 12px;" @click="previous">上一步</el-button>
          <el-button style="margin-top: 12px;" type="primary" @click="next">下一步</el-button>
        </div>

        <!-- 添加和修改章节表单 -->
        <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
          <el-form :model="chapter" label-width="120px">
            <el-form-item label="章节标题">
              <el-input v-model="chapter.title"/>
            </el-form-item>
            <el-form-item label="章节排序">
              <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogChapterFormVisible = false">取消</el-button>
            <el-button @click="saveOrUpdate" type="primary" :disabled="saveBtnDisabled">确定</el-button>
          </div>
        </el-dialog>

        <!-- 添加和修改课时表单 -->
        <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
          <el-form :model="video" label-width="120px">
            <el-form-item label="课时标题">
              <el-input v-model="video.title"/>
            </el-form-item>
            <el-form-item label="课时排序">
              <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
            </el-form-item>
            <el-form-item label="是否免费">
              <el-radio-group v-model="video.free">
                <el-radio :label="0">免费</el-radio>
                <el-radio :label="1">收费</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="上传视频">
              <el-upload
                :before-upload="beforeUploadFile"
                :on-success="handleVodUploadSuccess"
                :on-remove="handleVodRemove"
                :before-remove="beforeVodRemove"
                :on-exceed="handleUploadExceed"
                :file-list="fileList"
                :action="BASE_API+'/admin/vod/media/upload'"
                :limit="1"
                class="upload-demo">
                <el-button size="small" type="primary">上传视频</el-button>
                <el-tooltip placement="right-end">
                    <div slot="content">最大支持1G，<br>
                        支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                        GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                        MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                        SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
                    <i class="el-icon-question"/>
                </el-tooltip>
              </el-upload>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogVideoFormVisible = false">取消</el-button>
            <el-button @click="saveOrUpdateVideo" type="primary" :disabled="saveVideoBtnDisabled">确定</el-button>
          </div>
        </el-dialog>
    </div>
</template>
<script>
  import chapterApi from '@/api/chapter'
  import video from '@/api/video'
  import vod from '@/api/vod'
  export default {
    data() {
      return {
        courseId: '', //所属课程
        chapterNestedList: [], //章节嵌套课时列表
        dialogChapterFormVisible: false,  //是否显示章节表单
        chapter: {            //章节对象
          id: '',
          courseId: '',
          title: '',
          sort: 0
        },
        saveBtnDisabled: false,    //保存按钮是否禁用
        dialogVideoFormVisible: false,  //是否显示课时表单
        saveVideoBtnDisabled: false,    //课时保存按钮是否禁用
        chapterId: '',              //课时所在的章节id
        video: {                    // 课时对象
          id: '',
          courseId: '',
          chapterId: '',
          title: '',
          videoSourceId: '',
          sort: 0,
          free: 0,
          videoOriginalName: ''
        },
        BASE_API: process.env.BASE_API, //接口地址
        fileList: [],                   //上传文件列表
      };
    },

    created(){      
      this.init()
    },

    methods: {
      init(){
        if(this.$route.params && this.$route.params.id){
          this.courseId = this.$route.params.id
          this.fetchChapterNestedListByCourseId()
        }
      },

      fetchChapterNestedListByCourseId() {
        chapterApi.getNestedTreeList(this.courseId).then(response => {
          this.chapterNestedList = response.data.items
        })
      },

      saveOrUpdate() {
        this.saveBtnDisabled = true
        if(this.chapter.id){
          this.updateData()
        }else{
          this.saveData()
        }
      },

      updateData(){
        chapterApi.update(this.chapter).then(response => {
          this.$message({
            type: 'success',
            message: '修改成功！'
          })
          this.helpSave();
        })
      },

      saveData(){
        this.chapter.courseId = this.courseId
        chapterApi.save(this.chapter).then(response => {
          this.$message({
            type: 'success',
            message: '保存成功！'
          })
          this.helpSave();
        })
      },

      helpSave(){
        this.dialogChapterFormVisible = false     //保存成功关闭对话框
        this.fetchChapterNestedListByCourseId()   //刷新列表
        this.chapter.id = ''
        this.chapter.title = ''
        this.chapter.sort = 0
        this.saveBtnDisabled = false
      },

      //弹出新增章节对话框
      addChapter(){
        this.dialogChapterFormVisible = true
        this.chapter = {}
      },

      //弹出修改章节对话框
      editChapter(chapterId){
        this.dialogChapterFormVisible = true
        chapterApi.getById(chapterId).then(response => {
          this.chapter = response.data.item
        })
      },

      //删除章节
      removeChapter(chapterId){
        this.$confirm('此操作将永久删除该记录，是否继续？','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          return chapterApi.removeById(chapterId)
        }).then(() => {
          this.fetchChapterNestedListByCourseId()   //刷新列表
          this.$message({
            type: 'success',
            message: '删除成功！'
          })
        })
      },
      
      saveOrUpdateVideo() {
        this.saveVideoBtnDisabled = true
        if(this.video.id){
          this.updateDataVideo()
        }else{
          this.saveDataVideo()
        }
      },

      updateDataVideo(){
        video.updateVideoInfo(this.video).then(response => {
          this.$message({
            type: 'success',
            message: '修改成功！'
          })
          this.helpSaveVideo();
        })
      },

      saveDataVideo(){
        this.video.courseId = this.courseId
        this.video.chapterId = this.chapterId
        video.saveVideoInfo(this.video).then(response => {
          this.$message({
            type: 'success',
            message: '保存成功！'
          })
          this.helpSaveVideo();
        })
      },

      helpSaveVideo(){
        this.dialogVideoFormVisible = false     //保存成功关闭对话框
        this.fetchChapterNestedListByCourseId()   //刷新列表
        this.resetVideo()
        this.saveVideoBtnDisabled = false
      },
      
      //弹出新增小节对话框
      addVideo(chapterIdTmp){
        this.dialogVideoFormVisible = true
        this.chapterId = chapterIdTmp
        this.resetVideo()
      },

      //清空课时
      resetVideo(){
        this.video.id = ''
        this.video.courseId = ''
        this.video.chapterId = ''
        this.video.title = ''
        this.video.videoSourceId = ''
        this.video.sort = 0
        this.video.free = 0
        this.fileList = []
      },

      //弹出修改小节对话框
      editVideo(videoId){
        this.dialogVideoFormVisible = true
        video.getVideoInfoById(videoId).then(response => {
          this.video = response.data.item
          if(this.video.videoSourceId){
            this.fileList = [{'name': this.video.videoOriginalName}]
          }else{
            this.fileList = []
          }          
        })
      },

      removeVideo(videoId){
        this.$confirm('此操作将永久删除该记录，是否继续？','提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          return video.removeById(videoId)
        }).then(() => {
          this.fetchChapterNestedListByCourseId()   //刷新列表
          this.$message({
            type: 'success',
            message: '删除成功！'
          })
        })
      },

      previous() {
        this.$router.push({path: '/course/info/' + this.courseId})
      },
      next() {
        this.$router.push({path: '/course/publish/' + this.courseId})
      },
      //视频刚选择完，上传前会执行
      beforeUploadFile(file){
          this.saveVideoBtnDisabled = true
      },
      // 视频上传成功的回调
      handleVodUploadSuccess(response,file,fileList){
        this.video.videoSourceId = response.data.videoId
        this.video.videoOriginalName = file.name
        this.saveVideoBtnDisabled = false
      },
      //试图上传多于一个视频
      handleUploadExceed(files,fileList){
        this.$message.warning('想要重新上传视频，请先删除已上传的视频')
      },
      //删除点x的时候
      beforeVodRemove(files,fileList){
        return this.$confirm(`确定移除${files.name}?`)
      },
      //实际删除视频
      handleVodRemove(files,fileList){
        if(!this.video.videoSourceId){
          return
        }
        vod.removeVideo(this.video.videoSourceId).then(response => {
          this.video.videoSourceId = ''
          this.video.videoOriginalName = ''
          this.$message.success(response.message)
        })
      }
    }
  }
</script>
<style scoped>
.chapterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}

.chapterList li {
  position: relative;
}

.chapterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}

.chapterList .acts {
  float: right;
  font-size: 14px;
}

.videoList {
  padding-left: 50px;
}

.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dashed #DDD;
}
</style>