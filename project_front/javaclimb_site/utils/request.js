import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import cookie from 'js-cookie'

// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:8090', // api的base_url
  timeout: 12000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(
  config => {
    if(cookie.get('javaclim_jwt_token')){
      config.headers['token'] = cookie.get('javaclim_jwt_token')
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// respone拦截器
service.interceptors.response.use(
  response => {
    /**
     * code为非20000是抛错 可结合自己业务进行修改
     */
    const res = response.data
    if (res.code == 20000) {      
      return response.data
    } else if (res.code == 23004){
      // 获取用户信息失败,不希望弹出警告。直接清除cookie
      cookie.set('javaclim_jwt_token','',{domain: 'localhost'})
      return response.data
    } else if (res.code === 28004){
      // 需要登录  鉴权失败，跳转到登录页
      window.location.href = "/login"
      return
    } else if (res.code === 25000){
      // 支付中
      return response.data
    } else {
      Message({
        message: res.message || 'error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(error)
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
