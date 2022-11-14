import request from '@/utils/request'

export default {
    saveCourseInfo(courseInfoForm){
        return request({
            url: '/admin/edu/course/save-course-info',
            method: 'post',
            data: courseInfoForm
        })
    },

    //根据主键id获取课程信息
    getCourseInfoById(id){
        return request({
            url: `/admin/edu/course/course-info/${id}`,
            method: 'get'
        })
    },

    //更新课程基本信息
    updateCourseInfo(courseInfoForm){
        return request({
            url: '/admin/edu/course/update-course-info',
            method: 'put',
            data: courseInfoForm
        })
    },

    //获取课程分页列表
    getPageList(page,limit,searchObj){
        return request({
            url: `/admin/edu/course/list/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },

    //根据id删除课程
    removeById(id){
        return request({
            url: `/admin/edu/course/remove/${id}`,
        })
    }
}