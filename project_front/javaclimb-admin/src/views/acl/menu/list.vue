<template>
  <div class="app-container">
      <el-table :data="menuList" style="width:100%;margin:20px;" row-key="id" border default-expand-all 
        :tree-props="{children: 'children'}">
            <el-table-column prop="name" label="名称" width="180"></el-table-column>
            <el-table-column prop="path" label="访问路径" width="180"></el-table-column>
            <el-table-column prop="component" label="组件路径" width="180"></el-table-column>
            <el-table-column prop="permissionValue" label="权限值" width="180"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button v-if="scope.row.level == 1 || scope.row.level == 2" type="text" size="mini" 
                        @click="() => {dialogFormVisible = true,menu = {...menuForm},menu.pid = scope.row.id}">添加菜单</el-button>
                    <el-button v-if="scope.row.level == 3" type="text" size="mini"
                        @click="() => {dialogPermissionVisible = true,permission = {...perForm},permission.pid = scope.row.id}">添加功能</el-button>
                    <el-button v-if="scope.row.level == 4" type="text" size="mini" @click="updatePermission(scope.row)">修改功能</el-button>
                    <el-button v-if="scope.row.level != 4" type="text" size="mini" @click="updateMenu(scope.row)">修改</el-button>
                    <el-button type="text" size="mini" @click="remove(scope.row)">删除</el-button>                    
                </template>   
            </el-table-column>    
      </el-table>

      <!-- 添加修改菜单的窗口 -->
      <el-dialog :visible.sync="dialogFormVisible" :title="dialogFormValue">
          <el-form ref="menu" :model="menu" label-width="120px" :rules="menuValidateRules">
              <el-form-item label="菜单名称" prop="name">
                  <el-input v-model="menu.name"/>
              </el-form-item>
              <el-form-item label="访问路径" prop="path">
                  <el-input v-model="menu.path"/>
              </el-form-item>
              <el-form-item label="组件路径" prop="component">
                  <el-input v-model="menu.component"/>
              </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="resetMenu()">取消</el-button>
              <el-button type="primary" @click="append()">确定</el-button>
          </div>
      </el-dialog>

      <!-- 添加修改功能的窗口 -->
      <el-dialog :visible.sync="dialogPermissionVisible" :title="dialogPermissionValue">
          <el-form ref="permission" :model="permission" label-width="120px" :rules="permissionValidateRules">
              <el-form-item label="功能名称" prop="name">
                  <el-input v-model="permission.name"/>
              </el-form-item>
              <el-form-item label="访问路径" prop="path">
                  <el-input v-model="permission.path"/>
              </el-form-item>
              <el-form-item label="组件路径" prop="component">
                  <el-input v-model="permission.component"/>
              </el-form-item>
              <el-form-item label="功能权限值" prop="permissionValue">
                  <el-input v-model="permission.permissionValue"/>
              </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="resetPermission()">取消</el-button>
              <el-button type="primary" @click="appendPermission()">确定</el-button>
          </div>
      </el-dialog>
  </div>
</template>

<script>
import menu from '@/api/acl/menu'

//菜单form
const menuForm = {
    name: '',       //名称
    path: '',       //访问路径
    pid: 0,         //父id
    component: '',  //组件路径   
    type: '1'       //1表示菜单
}

//功能form
const perForm = {
    name: '',       //名称
    path: '',       //访问路径
    pid: 0,         //父id
    component: '',  //组件路径   
    type: '2',      //2表示功能
    permissionValue: '' //权限值
}

export default {

    data() {
        return {
            menuList: [],   //递归获取的全部菜单
            dialogFormVisible: false,      //添加菜单的窗口是否显示
            dialogPermissionVisible: false,      //添加功能的窗口是否显示
            dialogFormValue: "添加菜单",    //添加菜单或修改菜单
            dialogPermissionValue: "添加功能",    //添加菜单或修改菜单
            menu: menuForm,                 //添加或修改的菜单内容
            permission: perForm,            //添加或修改的功能内容
            menuValidateRules: {
                name: [{required:true,trigger:'blur',message:'菜单名称必须输入'}],
                path: [{required:true,trigger:'blur',message:'菜单路径必须输入'}],
                component: [{required:true,trigger:'blur',message:'组件名称必须输入'}]                
            },
            permissionValidateRules: {
                name: [{required:true,trigger:'blur',message:'菜单名称必须输入'}],
                permissionValue: [{required:true,trigger:'blur',message:'功能权限值必须输入'}]   
            }
        }
    },

    created() {
        this.fetchNodeList()
    },

    methods: {
        //递归获取全部菜单
        fetchNodeList() {
            menu.getNestedTreeList().then(response => {
                if(response.success === true) {
                    this.menuList = response.data.list
                    console.log(this.menuList)
                }
            })
        },

        //添加下级菜单或修改本级菜单
        append() {
            this.$refs.menu.validate(valid => {
                if(valid){
                    if(this.menu.id){       //修改本级菜单
                        this.update(this.menu)
                    }else{                  //添加下级菜单
                        this.appendMenu()
                    }
                }
            })
        },

        //添加下级菜单
        appendMenu(){
            menu.saveMenu(this.menu).then(response => {
                this.dialogFormVisible = false
                this.$message({
                    type: 'success',
                    message: '添加菜单成功'
                })
                //刷新页面
                this.fetchNodeList()
                this.menu = {...menuForm}
            })
        },

        //添加下级功能
        appendPermission(){
            this.$refs.permission.validate(valid => {
                if(valid){
                    if(this.permission.id){       //修改本级功能
                        this.update(this.permission)
                    }else{                          //添加下级功能
                        menu.saveMenu(this.permission).then(response => {
                            this.dialogPermissionVisible = false
                            this.$message({
                                type: 'success',
                                message: '添加功能成功'
                            })
                            //刷新页面
                            this.fetchNodeList()
                            this.permission = {...perForm}
                        })
                    }
                }
            })            
        },

        //取消菜单添加或修改
        resetMenu(){
            this.dialogFormVisible = false
            this.menu = {}
        },

        //取消功能添加或修改
        resetPermission(){
            this.dialogPermissionVisible = false
            this.permission = {}
        },

        //弹出修改菜单对话框
        updateMenu(data){
            this.dialogFormVisible = true
            this.menu = data
        },

        //弹出修改功能对话框
        updatePermission(data){
            this.dialogPermissionVisible = true
            this.permission = data
        },

        //修改菜单或功能
        update(obj){
            menu.update(obj).then(response => {
                this.dialogFormVisible = false
                this.dialogPermissionVisible = false
                this.$message({
                    type: 'success',
                    message: '修改成功'
                })
                //刷新页面
                this.fetchNodeList()
                this.menu = {}
                this.permission = {}
            })
        },

        //删除菜单或功能
        remove(data){
            this.$confirm('此操作将永久删除该记录，是否继续？','提示',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                return menu.removeById(data.id)
            }).then(() => {
                this.fetchNodeList()
                this.$message({
                    type: 'success',
                    message: '删除成功'
                })
            })
        }
    }
}
</script>

<style>

</style>