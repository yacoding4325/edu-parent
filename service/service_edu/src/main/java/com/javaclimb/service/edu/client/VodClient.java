package com.javaclimb.service.edu.client;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.client.fallback.VodClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-01 下午 4:14
 */

@FeignClient(name = "service-vod", fallback = VodClientFallback.class)
@Component
public interface VodClient {

    /**
     * 根据视频id删除单个 云端视频
     */
    @DeleteMapping("/admin/vod/media/remove/{vodId}")
    public R  removeVideo(@PathVariable String vodId);

    /**
     * 根据视频id列表 删除 多个云端视频
     */
    @DeleteMapping("admin/vod/media/delete-batch")
    public R removeVideoList(@PathVariable ("videoIdList") List<String> videoIdList);

}
