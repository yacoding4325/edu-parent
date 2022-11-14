<template>
    <div class="app-container">
        <el-checkbox-group v-model="checkedRoles">
          <el-checkbox v-for="role in roles" :label="role.id" :key="role.id">{{role.roleName}}</el-checkbox>
        </el-checkbox-group>
        <br/>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="save">保存</el-button>
    </div>
</template>
<script>
import userApi from '@/api/acl/user'
export default {
    data() {
        return {
            saveBtnDisabled :false,
            checkedRoles: [],   //已选中
            roles: [],          //所有角色
            userId: ''
        }
    },

    watch: {
        $route(to,from){
            this.init()
        }        
    },

    created() {
        this.init()
    },

    methods: {
        init() {
            if(this.$route.params && this.$route.params.id){
                this.userId = this.$route.params.id
                this.fetchDataById(this.userId)
            }
        },
        fetchDataById(userId){
            userApi.toAssign(userId).then(response => {
                this.roles = response.data.allRolesList     //所有角色
                var jsonObj = response.data.assignRoles     //该用户的所有角色对象
                this.checkedRoles = this.getJsonToList(jsonObj,"id")
            })
        },

        //获取所有的角色id列表
        getJsonToList(json,key) {
            //把JSON转换成对象
            var list = JSON.parse(JSON.stringify(json))
            var strText = []
            //遍历list，获取key的值
            for(var i=0;i<list.length;i++){
                strText.push(list[i][key])
            }
            return strText
        },

        //保存已选择的菜单项
        save() {
            this.saveBtnDisabled = true
            var ids = this.checkedRoles.join(",")
            if(ids === ','){
                ids=[]
            }
            userApi.doAssign(this.userId,ids).then(response => {
                if(response.success){
                    this.$message({
                        type: 'success',
                        message: '保存成功'
                    })
                    this.$router.push({path: '/acl/user/list'})
                }
            })
        }

    }
}
</script>