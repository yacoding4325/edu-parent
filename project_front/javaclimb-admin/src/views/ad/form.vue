<template>
    <div class="app-container">
        <!-- 输入表单 -->
        <el-form label-width="120px">
            <el-form-item label="广告推荐名称">
                <el-input v-model="ad.title"/>
            </el-form-item>
            <el-form-item label="推荐位">
                <el-select v-model="ad.typeId" placeholder="请选择推荐位">
                    <el-option v-for="adType in adTypeList" :key="adType.id" :label="adType.title" :value="adType.id"/>
                </el-select>
            </el-form-item>
            <el-form-item label="排序">
                <el-input-number v-model="ad.sort" :min="0"/>
            </el-form-item>
            <el-form-item label="广告图片">
                <el-upload 
                    :action="BASE_API+'/admin/oss/file/upload?module=ad'"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :on-error="handleAvatarError"
                    :before-upload="beforeAvatarUpload"
                    class="avartar-uploader">
                  <img v-if="ad.imageUrl" :src="ad.imageUrl">
                  <i v-else class="el-icon-plus avatar-uploader-icon"/>
                </el-upload>
            </el-form-item>
            <el-form-item label="背景颜色">
                <el-color-picker v-model="ad.color"/>
            </el-form-item>
            <el-form-item label="链接地址">
                <el-input v-model="ad.linkUrl"/>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :disabled="saveBtnDisabled" @click="saveOrUpdate()">{{saveOrUpdateVal}}</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import adTypeApi from '@/api/adType'
import adApi from '@/api/ad'
export default {
    data() {
        return {
            //广告推荐对象
            ad: {
                id: '',
                title: '',
                typeId: '',
                imageUrl: '',
                color: '',
                linkUrl: '',
                sort: 0
            },
            fileList: [],       //上传文件列表
            adTypeList: [],     //推荐位列表
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
                this.ad = {sort: 0}
                this.saveOrUpdateVal = '保存'
            }
            this.initAdTypeList()
        },
        //获取推荐位列表
        initAdTypeList(){
            adTypeApi.getList().then(response => {
                this.adTypeList = response.data.items
            })
        },

        //新增保存或者更改数据的判断入口
        saveOrUpdate() {
            this.saveBtnDisabled = true
            if(this.ad.id) {
                this.updateData()
            }else{
                this.saveData()
            }
        },

        //数据保存
        saveData() {
            adApi.save(this.ad).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/ad/list'})
            })
        },

        //根据id查询数据，用于数据更改时的回显
        fetechDataById(id) {
            adApi.getById(id).then(resp => {
                this.ad = resp.data.item
            })
        },

        //更新广告位
        updateData() {
            adApi.updateById(this.ad).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/ad/list'})
            })
        },
        // 上传成功的钩子
        handleAvatarSuccess(response) {
            if(response.success) {
                this.$message.success(response.message)
                this.ad.imageUrl = response.data.url
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