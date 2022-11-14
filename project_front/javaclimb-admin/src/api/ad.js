import request from '@/utils/request'

export default{
    //广告推荐列表分页查询
    pageList(page,limit) {
        return request({
            url: `/admin/cms/ad/list/${page}/${limit}`,
            method: 'get'
        })
    },

    //单个广告推荐删除
    deleteAdTypeById(id) {
        return request({
            url: `/admin/cms/ad/remove/${id}`,
            method: 'delete'
        })
    },
    
    //批量删除广告推荐
    batchRemove(idList){
        return request({
            url: `/admin/cms/ad/batch-remove`,
            method: 'delete',
            data: idList
        })
    },

    //新增广告推荐
    save(adType) {
        return request({
            url: `/admin/cms/ad/save`,
            method: 'post',
            data: adType
        })
    },

    //根据id获取到广告推荐详情
    getById(id) {
        return request({
            url: `/admin/cms/ad/get/${id}`,
            method: 'get'
        })
    },

    //更改广告推荐
    updateById(adType) {
        return request({
            url: `/admin/cms/ad/update`,
            method: 'post',
            data: adType
        })
    },

    //查询所有广告推荐
    getList() {
        return request({
            url: '/admin/cms/ad/findAll',
            method: 'get'
        })
    }
} 