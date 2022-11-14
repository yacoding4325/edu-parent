<template>
  <!-- 公共头 -->
  <header id="header">
    <section class="container">
      <h1 id="logo">
        <a href="/" title="攀登">
          <img src="~/assets/img/logo.gif" width="100%" alt="攀登">
        </a>
      </h1>
      <div class="h-r-nsl">
        <ul class="nav">
          <router-link to="/" tag="li" active-class="current" exact>
            <a>首页</a>
          </router-link>
          <router-link to="/course" tag="li" active-class="current">
            <a>课程</a>
          </router-link>
          <router-link to="/teacher" tag="li" active-class="current">
            <a>名师</a>
          </router-link>
        </ul>
        <!-- / nav -->
        <ul class="h-r-login">
          <li v-if="!userInfo" id="no-login">
            <a href="/login" title="登录">
              <em class="icon18 login-icon">&nbsp;</em>
              <span class="vam ml5">登录</span>
            </a>
            |
            <a href="/register" title="注册">
              <span class="vam ml5">注册</span>
            </a>
          </li>
          <li v-if="userInfo" id="is-login-two" class="h-r-user">
            <a href="/ucenter/order" title>
              <img
                :src="userInfo.avatar"
                width="30"
                height="30"
                class="vam picImg"
                alt
              >
              <span id="userName" class="vam disIb">张三</span>
            </a>
            <a href="javascript:void(0)" title="退出" class="ml5" @click="logout()">退出</a>
          </li>
        </ul>
        <aside class="h-r-search">
          <form action="#" method="post">
            <label class="h-r-s-box">
              <input type="text" placeholder="搜索课程" name="queryCourse.courseName" value>
              <button type="submit" class="s-btn">
                <em class="icon18">&nbsp;</em>
              </button>
            </label>
          </form>
        </aside>
      </div>
      <aside class="mw-nav-btn">
        <div class="mw-nav-icon"/>
      </aside>
      <div class="clear"/>
    </section>
  </header>
  <!-- /公共头 -->
</template>
<script>
import loginApi from '~/api/login'
import cookie from 'js-cookie'

export default{
  data() {
    return {
      userInfo: null
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() {
      //如果cookie中没有token，不显示用户信息
      if(!cookie.get("javaclim_jwt_token")){
        return
      }
      loginApi.getLoginInfo().then(resp => {
        //渲染页面
        this.userInfo = resp.data.userInfo
      })
    },
    //退出
    logout() {
      cookie.set("javaclim_jwt_token","",{domain: "localhost"})
      window.location.href="/"
    }
  }
}
</script>
