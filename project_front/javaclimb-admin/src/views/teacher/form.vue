<template>
    <div class="app-container">
        <!-- 输入表单 -->
        <el-form label-width="120px">
            <el-form-item label="讲师名称">
                <el-input v-model="teacher.name"/>
            </el-form-item>
            <el-form-item label="入驻时间">
                <el-date-picker v-model="teacher.joinDate" value-format="yyyy-MM-dd"/>
            </el-form-item>
            <el-form-item label="讲师排序">
                <el-input-number v-model="teacher.sort" :min="0"/>
            </el-form-item>
            <el-form-item label="讲师头衔">
                <el-select v-model="teacher.level" clearable placeholder="讲师头衔">
                    <el-option :value="1" label="高级讲师"></el-option>
                    <el-option :value="2" label="首席讲师"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="讲师简介">
                <el-input v-model="teacher.intro"/>
            </el-form-item>
            <el-form-item label="讲师资历">
                <el-input v-model="teacher.career" :rows="10" type="textarea"/>
            </el-form-item>
            <!-- 讲师头像上传 -->
            <el-form-item label="讲师头像">
                <el-upload 
                    :action="BASE_API+'/admin/oss/file/upload?module=avatar'"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :on-error="handleAvatarError"
                    :before-upload="beforeAvatarUpload"
                    class="avartar-uploader">
                  <img v-if="teacher.avatar" :src="teacher.avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"/>
                </el-upload>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" :disabled="saveBtnDisabled" @click="saveOrUpdate()">{{saveOrUpdateVal}}</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import teacherApi from '@/api/teacher'
export default {
    data() {
        return {
            //讲师对象
            teacher: {
                name: '',
                intro: '',
                career: '',
                level: 1,
                avatar: '',
                sort: 0,
                joinDate: ''
            },
            // 保存按钮默认可用，点击之后变成true，防止表单重复提交
            saveBtnDisabled: false,
            saveOrUpdateVal: '保存',
            BASE_API: process.env.BASE_API
        }
    },

    watch: {
        $route(to,from){
            this.init();
        }
    },

    created() {
        this.init();
    },

    methods:{
        init(){
            if(this.$route.params && this.$route.params.id) {       //更改
                this.fetechDataById(this.$route.params.id)
                this.saveOrUpdateVal = '修改'
            }else{
                this.teacher = {}
                this.saveOrUpdateVal = '保存'
            }
        },
        //新增保存或者更改数据的判断入口
        saveOrUpdate() {
            this.saveBtnDisabled = true
            if(this.teacher.id) {
                this.updateData()
            }else{
                this.saveData()
            }
        },

        //数据保存
        saveData() {
            teacherApi.save(this.teacher).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/teacher/list'})
            })
        },

        //根据id查询数据，用于数据更改时的回显
        fetechDataById(id) {
            teacherApi.getById(id).then(resp => {
                this.teacher = resp.data.item
            })
        },

        //更新讲师
        updateData() {
            teacherApi.updateById(this.teacher).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/teacher/list'})
            })
        },
        // 上传成功的钩子
        handleAvatarSuccess(response) {
            if(response.success) {
                this.$message.success(response.message)
                this.teacher.avatar = response.data.url
                this.$forceUpdate()
            }else{
                this.$message.error(response.message)
            }
        },
        //上传失败的钩子
        handleAvatarError() {
            this.$message.error(response.message)
        },
        //文件上传之前的钩子，可以用语校验
        beforeAvatarUpload(file) {
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
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar-uploader img {
  width: 178px;
  height: 178px;
  display: block;
}
</style>