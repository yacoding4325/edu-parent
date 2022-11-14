import request from '@/utils/request'

const api_name = '/admin/acl/user'

export default{
    //用户列表分页查询
    pageList(page,limit,searchObj) {
        return request({
            url: `${api_name}/list/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },

    //单个用户删除
    removeById(id) {
        return request({
            url: `${api_name}/remove/${id}`,
            method: 'delete'
        })
    },
    
    //新增用户
    save(role) {
        return request({
            url: `${api_name}/save`,
            method: 'post',
            data: role
        })
    },

    //根据id获取到用户详情
    getById(id) {
        return request({
            url: `${api_name}/get/${id}`,
            method: 'get'
        })
    },

    //更改用户
    updateById(role) {
        return request({
            url: `${api_name}/update`,
            method: 'post',
            data: role
        })
    },

    //查询所有用户
    getList() {
        return request({
            url: `${api_name}/findAll`,
            method: 'get'
        })
    },

    //根据用户获取角色
    toAssign(userId){
        return request({
            url: `${api_name}/toAssign/${userId}`,
            method: 'get'
        })
    },

    //给用户分配角色
    doAssign(userId,roleIds){
        return request({
            url: `${api_name}/doAssign`,
            method: 'post',
            params: {userId,roleIds}
        })
    }
} 