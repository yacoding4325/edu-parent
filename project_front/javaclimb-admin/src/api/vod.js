import request from '@/utils/request'

export default {
    //删除云端视频
    removeVideo(id){
        return request({
            url: `/admin/vod/media/remove/${id}`,
            method: 'delete'
        })
    }
}