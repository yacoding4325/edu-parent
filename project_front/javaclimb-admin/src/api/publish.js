import request from '@/utils/request'

const api_name = "/admin/edu/course"

export default {
    
    //根据id获取课程发布信息
    selectCoursePublishVoById(id) {
        return request({
            url: `${api_name}/course-publish-info/${id}`,
            method: 'get'
        })
    },

    // 根据id发布课程
    publishCourse(id) {
        return request({
            url: `${api_name}/publish-course/${id}`,
            method: 'put'
        })
    }
}