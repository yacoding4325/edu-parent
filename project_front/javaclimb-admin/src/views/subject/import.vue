<template>
<div class="app-container">
    <el-form label-width="120px">
        <el-form-item label="信息描述">
            <el-tag type="info">excel模版说明</el-tag>
            <el-tag>
                <i class="el-icon-download"/>
                <a :href="defaultExcelTemplate">点击下载模版</a>
            </el-tag>
        </el-form-item>
        <el-form-item label="选择Excel">
            <el-upload 
                ref="upload"
                :action="BASE_API+'/admin/edu/subject/import'"
                :auto-upload="false"
                :on-exceed="fileUploadExceed"
                :on-success="fileUploadSuccess"
                :on-error="fileUploadError"
                :limit="1"
                name="file"
                accept="application/vnd.ms-excel"
                >
                <el-button size="small" type="primary" slot="trigger">选取文件</el-button>
                <el-button size="small" type="success" style="margin-left: 10px;" @click="submitUpload()" :disabled="importBtnDisabled">导入</el-button>
            </el-upload>
        </el-form-item>
    </el-form>
</div>
</template>

<script>
export default {
    name: 'SubjectImport',
    data() {
        return {
            BASE_API: process.env.BASE_API,         //接口API地址
            defaultExcelTemplate: '/static/template.xls',   //默认Excel模版
            importBtnDisabled: false                    //导入按钮是否禁用
        }
    },
    methods: {
        //上传文件数多于约定数量时触发
        fileUploadExceed() {
            this.$message.warning('只能选取一个文件')
        },

        //上传
        submitUpload() {
            this.importBtnDisabled = true
            this.$refs.upload.submit()          //提交上传请求
        },

        //上传成功，返回信息
        fileUploadSuccess(response){
            this.importBtnDisabled = false
            this.$message.success(response.message)
            this.$refs.upload.clearFiles()          //清空文件列表
        },

        //上传失败
        fileUploadError(err){
            this.importBtnDisabled = false
            this.$message.error(err.message)
            this.$refs.upload.clearFiles()          //清空文件列表
        }
    }
}
</script>