package com.javaclimb.service.vod.controller;

/**
 * @Author yaCoding
 * @create 2022-10-31 下午 8:07
 */

import com.aliyun.oss.ClientException;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.base.utils.ExceptionUtils;
import com.javaclimb.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 阿里云视频点播controller
 */
@Api(description = "阿里云视频点播")
@RestController
@RequestMapping("admin/vod/media")
@Slf4j
@CrossOrigin
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("上传视频")
    @PostMapping("upload")
    public R upload(@ApiParam(value="视频文件",required = true) @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            String orginalFileName = file.getOriginalFilename();
            String videoId = videoService.uploadVideo(inputStream,orginalFileName);
            return R.ok().message("视频上传成功").data("videoId",videoId);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new JavaclimbException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }
    }

    @ApiOperation("删除云端视频")
    @DeleteMapping("remove/{vodId}")
    public R removeVideo (@ApiParam (value = "阿里云视频id",readOnly = true) @PathVariable String vodId) {
        try {
            videoService.removeVideo(vodId);
            return R.ok().message("视频删除成功");
        } catch (ClientException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new JavaclimbException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }
    }

    @ApiOperation("批量删除云端视频")
    @DeleteMapping("delete-batch")
    public R removeVideoList(@ApiParam(value = "阿里云视频id列表",required = true)@RequestParam("videoIdList") List<String> videoIdList){
        try {
            videoService.removeVideoList(videoIdList);
            return R.ok().message("视频列表删除成功");
        } catch (ClientException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new JavaclimbException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

}
