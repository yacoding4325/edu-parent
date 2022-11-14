import request from '@/utils/request'

const api_name = '/admin/acl/role'

export default{
    //角色列表分页查询
    pageList(page,limit,searchObj) {
        return request({
            url: `${api_name}/list/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },

    //单个角色删除
    removeById(id) {
        return request({
            url: `${api_name}/remove/${id}`,
            method: 'delete'
        })
    },
    
    //新增角色
    save(role) {
        return request({
            url: `${api_name}/save`,
            method: 'post',
            data: role
        })
    },

    //根据id获取到角色详情
    getById(id) {
        return request({
            url: `${api_name}/get/${id}`,
            method: 'get'
        })
    },

    //更改角色
    updateById(role) {
        return request({
            url: `${api_name}/update`,
            method: 'post',
            data: role
        })
    },

    //查询所有角色
    getList() {
        return request({
            url: `${api_name}/findAll`,
            method: 'get'
        })
    }
} 