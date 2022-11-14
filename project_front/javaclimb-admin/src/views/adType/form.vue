<template>
    <div class="app-container">
        <!-- 输入表单 -->
        <el-form label-width="120px">
            <el-form-item label="推荐位名称">
                <el-input v-model="adType.title"/>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :disabled="saveBtnDisabled" @click="saveOrUpdate()">{{saveOrUpdateVal}}</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import adTypeApi from '@/api/adType'
export default {
    data() {
        return {
            //广告位对象
            adType: {
                title: ''
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
                this.adType = {}
                this.saveOrUpdateVal = '保存'
            }
        },
        //新增保存或者更改数据的判断入口
        saveOrUpdate() {
            this.saveBtnDisabled = true
            if(this.adType.id) {
                this.updateData()
            }else{
                this.saveData()
            }
        },

        //数据保存
        saveData() {
            adTypeApi.save(this.adType).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/ad/type-list'})
            })
        },

        //根据id查询数据，用于数据更改时的回显
        fetechDataById(id) {
            adTypeApi.getById(id).then(resp => {
                this.adType = resp.data.item
            })
        },

        //更新广告位
        updateData() {
            adTypeApi.updateById(this.adType).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/ad/type-list'})
            })
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