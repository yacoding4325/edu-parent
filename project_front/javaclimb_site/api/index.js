import request from '~/utils/request'
export default {
    //获取首页顶部轮播图列表
    getTopBannerAdList(){
        return request({
            //因为这个是专门为了轮播图设计的，所以可以硬编码，戴上参数1
            url: '/api/cms/ad/list/1',
            method: 'get'
        })
    },
    //获取课程和讲师的首页数据
    getIndexData(){
        return request({
            url: '/api/edu/index',
            method: 'get'
        })
    }
}