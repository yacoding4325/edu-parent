<template>
    <div class="Page confirm">
        <div class="title">
            <h1 class="f1 f18">订单确认</h1>
            <div class="clear"></div>
        </div>
        <form name="flowForm" id = "flowForm" method="post" action="">
            <table class="GoodList">
                <tbody>
                    <th class="name">商品</th>
                    <th class="price">原价</th>
                    <th class="priceNew">价格</th>                    
                </tbody>
                <tr>
                    <td colspan="3" class="teacher">讲师：{{order.teacherName}}</td>
                </tr>
                <tr class="good">
                    <td class="name First">
                        <div><a :href="'/course/'+order.courseId" target="_blank"><img :src="order.courseCover"></a></div>
                        <div class="goodInfo"><a :href="'/course/'+order.courseId" target="_blank">{{order.courseTitle}}</a></div>
                    </td>
                    <td class="price">
                        <p>￥<Strong>{{order.totalFee}}</strong></p>
                    </td>
                    <td class="red priceNew Last">
                        <p>￥<Strong>{{order.totalFee}}</strong></p>
                    </td>
                </tr>
                <tr>
                    <td class="Billing tr" colspan="3">
                        共<strong class="red">1</strong>件商品，合计<strong class="red">￥{{order.totalFee}}</strong>
                    </td>
                </tr>
            </table>
            <div class="Finish">
                <el-button type="danger" @click="toPay()">去支付</el-button></el-button>
            </div>
        </form>
    </div>
</template>
<script>
import orderApi from '@/api/order'
export default {
    asyncData({params,error}){
        return orderApi.getById(params.id).then(response => {
            return {
                order: response.data.item
            }
        })
    },

    methods: {
        toPay(){
            this.$router.push({path:'/pay/'+this.order.id})
        }
    }
}
</script>