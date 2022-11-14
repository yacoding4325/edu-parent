import request from '~/utils/request'
export default {
    //获取播放凭证
    getPlayAuth(videoId){
        return request({
            url: `/api/vod/video/get-play-auth/${videoId}`,
            method: 'get'
        })
    }
}