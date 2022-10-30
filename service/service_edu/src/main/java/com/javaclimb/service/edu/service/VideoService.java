package com.javaclimb.service.edu.service;

import com.javaclimb.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.service.edu.entity.form.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *

 */
public interface VideoService extends IService<Video> {
    /**
     * 判断某个章节下还有没有视频
     */
    boolean getCountByChapterId(String chapterId);

    /**
     * 保存一个课程视频
     */
    void saveVideoInfo(VideoInfoForm videoInfoForm);

    /**
     * 根据主键id获取课程视频对象
     */
    VideoInfoForm getVideoInfoFormById(String id);

    /**
     * 更新一个课程视频
     */
    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    /**
     * 根据主键id删除课程视频
     */
    boolean removeVideoById(String id);
}
