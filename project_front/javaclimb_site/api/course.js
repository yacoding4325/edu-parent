import request from '~/utils/request'
export default {
    //分页课程列表
    getPageList(page,limit,searchObj){
        return request({
            url: `/api/edu/course/list/${page}/${limit}`,
            method: 'post',
            data: searchObj
        })
    },
    //嵌套课程类别数据列表
    getNestedTreeList() {
        return request({
            url: `/api/edu/subject/nested-list`,
            method: 'get'
        })
    },
    //根据课程id查询课程详情
    getById(courseId) {
        return request({
            url: `/api/edu/course/getById/${courseId}`,
            method: 'get'
        })
    }
}