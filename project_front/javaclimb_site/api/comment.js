import request from '~/utils/request'
export default {
    //分页课程评论列表
    getCommonList(page,limit,courseId){
        return request({
            url: `/api/edu/comment/list/${page}/${limit}/${courseId}`,
            method: 'get'
        })
    },

    //添加评论
    addComment(comment){
        return request({
            url: `/api/edu/comment/save`,
            method: 'post',
            data: comment
        })
    }
}