import request from '~/utils/request'
export default {
    //分页讲师列表
    getPageList(page,limit){
        return request({
            url: `/api/edu/teacher/list/${page}/${limit}`,
            method: 'get'
        })
    },
    //根据讲师id获取讲师详情，带他讲的课程列表
    getTeacherById(teacherId){
        return request({
            url: `/api/edu/teacher/get/${teacherId}`,
            method: 'get'
        })
    }
}