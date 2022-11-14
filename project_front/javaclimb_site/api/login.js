import request from '~/utils/request'
import cookie from 'js-cookie'

export default {
    //会员登录
    submitLogin(member){
        return request({
            url: '/api/ucenter/member/login',
            method: 'post',
            data: member
        })
    },

    //根据token获取会员信息
    getLoginInfo(){
        return request({
            url: '/api/ucenter/member/get-login-info',
            method: 'get',
            //通过请求头发送token
            headers: {'token': cookie.get('javaclim_jwt_token')}
        })
    },
}