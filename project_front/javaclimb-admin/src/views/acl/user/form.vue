<template>
    <div class="app-container">
        <!-- 输入表单 -->
        <el-form label-width="120px">
            <el-form-item label="用户名">
                <el-input v-model="user.username"/>
            </el-form-item>
            <el-form-item label="用户昵称">
                <el-input v-model="user.nickName"/>
            </el-form-item>
            <el-form-item v-if="!user.id" label="用户密码">
                <el-input v-model="user.password"/>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :disabled="saveBtnDisabled" @click="saveOrUpdate()">{{saveOrUpdateVal}}</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import userApi from '@/api/acl/user'
export default {
    data() {
        return {
            //用户对象
            user: {
                username: '',
                nickName: '',
                password: ''
            },
            // 保存按钮默认可用，点击之后变成true，防止表单重复提交
            saveBtnDisabled: false,
            saveOrUpdateVal: '保存'
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
                this.user = {}
                this.saveOrUpdateVal = '保存'
            }
        },
        //新增保存或者更改数据的判断入口
        saveOrUpdate() {
            this.saveBtnDisabled = true
            if(this.user.id) {
                this.updateData()
            }else{
                this.saveData()
            }
        },

        //数据保存
        saveData() {
            userApi.save(this.user).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/acl/user/list'})
            })
        },

        //根据id查询数据，用于数据更改时的回显
        fetechDataById(id) {
            userApi.getById(id).then(resp => {
                this.user = resp.data.item
            })
        },

        //更新用户
        updateData() {
            userApi.updateById(this.user).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/acl/user/list'})
            })
        }
    }
    
}   
</script>
