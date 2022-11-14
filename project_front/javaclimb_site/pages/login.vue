<template>
    <div class="main">
        <div class="title">
            <a class="active" href="/login">登录</a>
            <span>·</span>
            <a href="/register">注册</a>
        </div>
        <div class="sign-up-container">
            <form action="register">
                <div class="input-prepend restyle">
                    <input v-model="member.mobile" type="text" placeholder="手机号"/>
                    <i class="iconfont icon-phone"/>
                </div>
                <div class="input-prepend">
                    <input v-model="member.password" type="password" placeholder="密码"/>
                    <i class="iconfont icon-password"/>
                </div>
                <div class="btn">
                    <input type="button" class="sign-up-button" value="登录" @click="submitLogin()"/>
                </div>
            </form>
        </div>
    </div>
</template>
<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import cookie from 'js-cookie'
import loginApi from '~/api/login'
export default {
    layout: 'sign',
    data() {
        return {
            member: {
                mobile: '',
                password: ''
            }
        }
    },

    methods: {
        submitLogin() {
            loginApi.submitLogin(this.member).then(resp => {
                //将jwt写入cookie
                //第三个参数是跨域问题，上线的时候改成自己的域名或ip
                cookie.set('javaclim_jwt_token',resp.data.token,{domain: 'localhost'})
                this.$message.success(resp.message)
                this.$router.push({path: 'login'})
                //如果上一页是注册页面，则跳转到网站首页，否则跳转到上一个刚刚访问的页面
                if(document.referrer.indexOf('register') === -1){
                    window.location.href = "/"
                }else{
                    history.go(-1)
                }
            })
        }
    }

}
</script>