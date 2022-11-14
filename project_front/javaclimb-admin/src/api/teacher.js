import request from '@/utils/request'

export default{
    //讲师列表分页查询
    pageList(page,limit,searchObj) {
        return request({
            // url: '/admin/edu/teacher/list/'+page+'/'+limit,
            url: `/admin/edu/teacher/list/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },

    //单个讲师删除
    deleteTeacherById(id) {
        return request({
            url: `/admin/edu/teacher/remove/${id}`,
            method: 'delete'
        })
    },
    
    //批量删除讲师
    batchRemove(idList){
        return request({
            url: `/admin/edu/teacher/batch-remove`,
            method: 'delete',
            data: idList
        })
    },

    //新增讲师
    save(teacher) {
        return request({
            url: `/admin/edu/teacher/save`,
            method: 'post',
            data: teacher
        })
    },

    //根据id获取到讲师详情
    getById(id) {
        return request({
            url: `/admin/edu/teacher/get/${id}`,
            method: 'get'
        })
    },

    //更改讲师
    updateById(teacher) {
        return request({
            url: `/admin/edu/teacher/update`,
            method: 'post',
            data: teacher
        })
    },

    //查询所有讲师
    getList() {
        return request({
            url: '/admin/edu/teacher/findAll',
            method: 'get'
        })
    }
} 