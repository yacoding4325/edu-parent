package com.javaclimb.service.vod.service;

/**
 * @Author yaCoding
 * @create 2022-10-31 下午 8:08
 */

import com.aliyun.oss.ClientException;

import java.io.InputStream;
import java.util.List;

/**
 * 视频点播service接口
 */
public interface VideoService {

    /**
     * 视频上传
     * @param inputStream           上传的文件流
     * @param originalFileName      视频原始名称
     * @return                      上传完成之后阿里云返回的videoId，唯一的视频id
     */
    String uploadVideo(InputStream inputStream,String originalFileName);

    /**
     * 根据视频videoId删除云端视频
     */
    void removeVideo (String videoId) throws ClientException;

    /**
     * 批量删除云端视频
     */
    void removeVideoList(List<String> videoIdList) throws ClientException;
}
