package com.javaclimb.service.vod.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.vod.service.VideoService;
import com.javaclimb.service.vod.utils.AliyunVodSdkUtils;
import com.javaclimb.service.vod.utils.VodProperties;
import com.javaclimb.service.base.exception.JavaclimbException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * 视频点播service实现类
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VodProperties vodProperties;

    /**
     * 视频上传
     * @param inputStream      上传的文件流
     * @param originalFileName 视频原始名称
     * @return 上传完成之后阿里云返回的videoId，唯一的视频id
     */
    @Override
    public String uploadVideo(InputStream inputStream, String originalFileName) {
        String title = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(vodProperties.getKeyId(), vodProperties.getKeySecret(), title, originalFileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();
        if (StringUtils.isEmpty(videoId)) {
            log.error("阿里云上传失败：" + response.getCode() + "-" + response.getMessage());
            throw new JavaclimbException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }
        return videoId;
    }

    /**
     * 根据视频videoId删除云端视频
     *
     * @param videoId
     */
    @Override
    public void removeVideo(String videoId) throws ClientException, com.aliyuncs.exceptions.ClientException {
        DefaultAcsClient client = AliyunVodSdkUtils.initVodClient(vodProperties.getKeyId(), vodProperties.getKeySecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        client.getAcsResponse(request);
    }


    /**
     * 批量删除云端视频
     *
     * @param videoIdList
     */
    @Override
    public void removeVideoList(List<String> videoIdList) throws ClientException, com.aliyuncs.exceptions.ClientException {
        DefaultAcsClient client = AliyunVodSdkUtils.initVodClient(vodProperties.getKeyId(), vodProperties.getKeySecret());
        String str = StringUtils.join(videoIdList.toArray(),",");
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(str);
        client.getAcsResponse(request);
    }

}