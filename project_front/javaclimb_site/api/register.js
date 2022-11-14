import request from '~/utils/request'
export default {
    //注册
    register(member){
        return request({
            url: '/api/ucenter/member/register',
            method: 'post',
            data: member
        })
    }
}