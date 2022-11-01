package com.javaclimb.service.edu.client.fallback;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-01 下午 4:18
 */

@Component
public class VodClientFallback implements VodClient {
    /**
     * 根据视频 id删除单个 云端视频
     * @param vodId
     * @return
     */
    @Override
    public R removeVideo(String vodId) {
        return R.error().message("time out");
    }

    /**
     * 根据视频 id列表 删除 多个云端视频
     * @param videoIdList
     * @return
     */
    @Override
    public R removeVideoList(List<String> videoIdList) {
        return R.error().message("time out");
    }
}
