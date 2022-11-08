package com.javaclimb.service.vod.controller;

/**
 * @Author yaCoding
 * @create 2022-11-05 下午 7:24
 */

import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.vod.utils.AliyunVodSdkUtils;
import com.javaclimb.service.vod.utils.VodProperties;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 阿里云视频点播微服务
 */
@Api(description = "阿里云视频点播微服务")
@RestController
@RequestMapping("api/vod/video")
@Slf4j
//@CrossOrigin
public class ApiVideoController {

    @Autowired
    private VodProperties vodProperties;

    @GetMapping("get-play-auth/{videoId}")
    public R getPlayAuth(@PathVariable("videoId") String videoId) throws Exception {
        //初始化
        DefaultAcsClient client = AliyunVodSdkUtils.initVodClient(vodProperties.getKeyId(), vodProperties.getKeySecret());
        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        //得到播放凭证
        String playAuth = response.getPlayAuth();
        //返回结果
        return R.ok().message("获取凭证成功").data("playAuth",playAuth);
    }
}
