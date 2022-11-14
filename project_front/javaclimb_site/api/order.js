import request from '~/utils/request'
export default {
    //创建订单
    createOrder(courseId){
        return request({
            url: `/api/trade/order/auth/save/${courseId}`,
            method: 'post'
        })
    },
    //根据订单id获取订单详情
    getById(orderId){
        return request({
            url: `/api/trade/order/auth/get/${orderId}`,
            method: 'get'
        })
    }

}