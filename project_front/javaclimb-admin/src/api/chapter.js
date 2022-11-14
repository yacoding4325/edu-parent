import request from '@/utils/request'

export default {
    getNestedTreeList(courseId) {
        return request({
            url: `/admin/edu/chapter/nested-list/${courseId}`,
            method: 'get'
        })
    },

    //新增章节
    save(chapter){
        return request({
            url: `/admin/edu/chapter/save`,
            method: 'post',
            data: chapter
        })
    },

    //根据主键id获取章节
    getById(id) {
        return request({
            url: `/admin/edu/chapter/get/${id}`,
            method: 'get'
        })
    },

    //修改章节
    update(chapter){
        return request({
            url: `/admin/edu/chapter/update`,
            method: 'post',
            data: chapter
        })
    },

    //删除章节
    removeById(id) {
        return request({
            url: `/admin/edu/chapter/remove/${id}`,
            method: 'get'
        })
    }
}