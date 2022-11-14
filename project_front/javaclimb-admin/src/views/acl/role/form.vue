<template>
    <div class="app-container">
        <!-- 输入表单 -->
        <el-form label-width="120px">
            <el-form-item label="角色名称">
                <el-input v-model="role.roleName"/>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :disabled="saveBtnDisabled" @click="saveOrUpdate()">{{saveOrUpdateVal}}</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import roleApi from '@/api/acl/role'
export default {
    data() {
        return {
            //角色对象
            role: {
                roleName: ''
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
                this.role = {}
                this.saveOrUpdateVal = '保存'
            }
        },
        //新增保存或者更改数据的判断入口
        saveOrUpdate() {
            this.saveBtnDisabled = true
            if(this.role.id) {
                this.updateData()
            }else{
                this.saveData()
            }
        },

        //数据保存
        saveData() {
            roleApi.save(this.role).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/acl/role/list'})
            })
        },

        //根据id查询数据，用于数据更改时的回显
        fetechDataById(id) {
            roleApi.getById(id).then(resp => {
                this.role = resp.data.item
            })
        },

        //更新讲师
        updateData() {
            roleApi.updateById(this.role).then(resp => {
                this.$message({
                    type: 'success',
                    message: resp.message
                })
                this.$router.push({path: '/acl/role/list'})
            })
        }
    }
    
}   
</script>
