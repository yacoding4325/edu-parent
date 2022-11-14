import request from '~/utils/request'
export default {
    //支付成功
    payOk(orderId){
        return request({
            url: `/api/trade/order/auth/payOk/${orderId}`,
            method: 'get'
        })
    }

}