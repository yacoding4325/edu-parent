import request from '@/utils/request'

export default{
    //广告位列表分页查询
    pageList(page,limit) {
        return request({
            url: `/admin/cms/ad-type/list/${page}/${limit}`,
            method: 'get'
        })
    },

    //单个广告位删除
    deleteAdTypeById(id) {
        return request({
            url: `/admin/cms/ad-type/remove/${id}`,
            method: 'delete'
        })
    },
    
    //批量删除广告位
    batchRemove(idList){
        return request({
            url: `/admin/cms/ad-type/batch-remove`,
            method: 'delete',
            data: idList
        })
    },

    //新增广告位
    save(adType) {
        return request({
            url: `/admin/cms/ad-type/save`,
            method: 'post',
            data: adType
        })
    },

    //根据id获取到广告位详情
    getById(id) {
        return request({
            url: `/admin/cms/ad-type/get/${id}`,
            method: 'get'
        })
    },

    //更改广告位
    updateById(adType) {
        return request({
            url: `/admin/cms/ad-type/update`,
            method: 'post',
            data: adType
        })
    },

    //查询所有广告位
    getList() {
        return request({
            url: '/admin/cms/ad-type/findAll',
            method: 'get'
        })
    }
} 