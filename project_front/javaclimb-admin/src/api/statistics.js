import request from '@/utils/request'

export default{
    //生成统计记录
    createStatistics(day) {
        return request({
            url: `/admin/statistics/daily/create/${day}`,
            method: 'post'
        })
    },

    //查询图表统计数据
    showChart(searchObj) {
        return request({
            url: `/admin/statistics/daily/show-chart/${searchObj.begin}/${searchObj.end}/${searchObj.type}`,
            method: 'get'
        })
    }
} 