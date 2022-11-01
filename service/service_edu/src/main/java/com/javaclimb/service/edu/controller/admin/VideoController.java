package com.javaclimb.service.edu.controller.admin;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.entity.form.VideoInfoForm;
import com.javaclimb.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 */
@Api(description = "课时管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/edu/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "新增课时")
    @PostMapping("save-video-info")
    public R save(@ApiParam(value = "章节对象",required = true)@RequestBody VideoInfoForm videoInfoForm) {
        videoService.saveVideoInfo(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据id查询课时")
    @GetMapping("video-info/{id}")
    public R getVideoInfoById(@ApiParam(value = "课时ID",required = true)@PathVariable String id) {
        VideoInfoForm videoInfoForm = videoService.getVideoInfoFormById(id);
        return R.ok().data("item",videoInfoForm);
    }

    @ApiOperation(value = "更新课时")
    @PostMapping("update-video-info")
    public R updateVideoInfo(@ApiParam(value = "章节对象",required = true)@RequestBody VideoInfoForm videoInfoForm) {
        videoService.updateVideoInfoById(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据id删除课时")
    @GetMapping("removeById/{id}")
    public R removeById(@ApiParam(value = "课时ID",required = true)@PathVariable String id) {
        boolean result = videoService.removeVideoById(id);
        if(result){
            return R.ok();
        }
        return R.error().message("删除失败");
    }
}