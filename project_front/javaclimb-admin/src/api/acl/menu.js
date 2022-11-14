import request from '@/utils/request'
const api_name = '/admin/acl/permission'

export default{

    //递归获取全部菜单
    getNestedTreeList() {
        return request({
            url: `${api_name}`,
            method: 'get'
        })
    },

    //新增菜单或功能
    saveMenu(menu){
        return request({
            url: `${api_name}/save`,
            method: 'post',
            data: menu
        })
    },

    //修改菜单或功能
    update(menu){
        return request({
            url: `${api_name}/update`,
            method: 'put',
            data: menu
        })
    },

    //删除菜单，使用递归删除
    removeById(id){
        return request({
            url: `${api_name}/remove/${id}`,
            method: 'delete'
        })
    },

    //根据角色id获取菜单
    toAssign(roleId){
        return request({
            url: `${api_name}/toAssign/${roleId}`,
            method: 'get'
        })
    },

    //给角色分配菜单权限
    doAssign(roleId,permissionIds){
        return request({
            url: `${api_name}/doAssign`,
            method: 'post',
            params: {roleId,permissionIds}
        })
    }
}