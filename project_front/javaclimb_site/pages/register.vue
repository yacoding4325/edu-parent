<template>
    <div class="main">
        <div class="title">
            <a href="/login">登录</a>
            <span>·</span>
            <a class="active" href="/register">注册</a>
        </div>
        <div class="sign-up-container">
            <form action="register">
                <div class="input-prepend restyle">
                    <input v-model="member.nickname" type="text" placeholder="你的昵称"/>
                    <i class="iconfont icon-user"/>
                </div>
                <div class="input-prepend restyle no-radius">
                    <input v-model="member.mobile" type="text" placeholder="手机号"/>
                    <i class="iconfont icon-phone"/>
                </div>
                <div class="input-prepend">
                    <input v-model="member.password" type="password" placeholder="设置密码"/>
                    <i class="iconfont icon-password"/>
                </div>
                <div class="btn">
                    <input type="button" class="sign-up-button" value="注册" @click="submitRegister()"/>
                </div>
                <p class="sign-up-msg">
                    点击“注册”即表示您同意并愿意遵守java攀登网
                    <br>
                    <a target="_blank" href="http://www.javaclimb.com/">用户协议</a>
                    和
                    <a target="_blank" href="http://www.javaclimb.com/">隐私政策</a>
                </p>
            </form>
        </div>
    </div>
</template>
<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import registerApi from '~/api/register'
export default {
    layout: 'sign',
    data() {
        return {
            member: {
                mobile: '',
                nickname: '',
                password: ''
            }
        }
    },

    methods: {
        submitRegister() {
            registerApi.register(this.member).then(resp => {
                this.$message.success(resp.message)
                this.$router.push({path: 'login'})
            })
        }
    }

}
</script>