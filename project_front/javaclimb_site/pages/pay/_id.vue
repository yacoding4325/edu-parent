<template>
    <div class="Page confirm">
        <div class="title">
            <h1 class="f1 f18">订单支付</h1>
            <div class="clear"></div>
        </div>
        <form name="flowForm" id = "flowForm" method="post" action="">            
            <div class="Finish">
                <el-button type="danger" @click="payOk()">支付成功</el-button></el-button>
            </div>
        </form>
    </div>
</template>
<script>
import payApi from '@/api/pay'
export default {
    methods: {
        payOk(){
            payApi.payOk(this.$route.params.id).then(response => {
                this.$router.push({path:'/course/'+response.data.item.courseId})
            })            
        }
    }
}
</script>