<template>
    <div style="margin:20px">
        <el-tree ref="tree" :data="data" :props="defaultProps" show-checkbox default-expand-all node-key="id" highlight-current />
        <el-button :disabled="saveBtnDisabled" type="primary" @click="save">保存</el-button>
    </div>
</template>
<script>
import menu from '@/api/acl/menu'
export default {
    data() {
        return {
            saveBtnDisabled :false,
            data: [],
            roleId: '',
            defaultProps: {
                children: "children",
                label: 'name'
            }
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
                this.roleId = this.$route.params.id
                this.fetchDataById(this.roleId)
            }
        },
        fetchDataById(roleId){
            menu.toAssign(roleId).then(response => {
                this.data = response.data.list
                var list = []
                var jsonList = JSON.parse(JSON.stringify(this.data))
                this.getJsonToList(list,jsonList[0]['children'])
                this.$refs.tree.setCheckedKeys(list)
            })
        },

        //获取所有的角色权限列表
        getJsonToList(list,jsonList) {
            for(var i=0;i<jsonList.length;i++){
                if(jsonList[i]['select'] == true && jsonList[i]['children'].length == 0){
                    list.push(jsonList[i]['id'])
                }
                if(jsonList[i]['children'].length > 0){
                    this.getJsonToList(list,jsonList[i]['children'])
                }
            }
        },

        //保存已选择的菜单项
        save() {
            this.saveBtnDisabled = true
            var ids = this.$refs.tree.getHalfCheckedKeys().join(',') + ',' + this.$refs.tree.getCheckedKeys().join(',')
            if(ids === ','){
                ids=[]
            }
            menu.doAssign(this.roleId,ids).then(response => {
                if(response.success){
                    this.$message({
                        type: 'success',
                        message: '保存成功'
                    })
                    this.$router.push({path: '/acl/role/list'})
                }
            })
        }

    }
}
</script>