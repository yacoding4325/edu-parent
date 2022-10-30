package com.javaclimb.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.edu.entity.Video;
import com.javaclimb.service.edu.entity.form.VideoInfoForm;
import com.javaclimb.service.edu.mapper.VideoMapper;
import com.javaclimb.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    /**
     * 判断某个章节下还有没有视频
     *
     * @param chapterId
     */
    @Override
    public boolean getCountByChapterId(String chapterId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count!=null && count > 0;
    }

    /**
     * 保存一个课程视频
     *
     * @param videoInfoForm
     */
    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm) {
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm,video);
        this.save(video);
    }

    /**
     * 根据主键id获取课程视频对象
     *
     * @param id
     */
    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
        Video video = this.getById(id);
        if(video == null) {
            throw new JavaclimbException(ResultCodeEnum.NO_DATA);
        }
        //创建VideoInfoForm对象
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(video,videoInfoForm);
        return videoInfoForm;
    }

    /**
     * 更新一个课程视频
     *
     * @param videoInfoForm
     */
    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm,video);
        this.updateById(video);
    }

    /**
     * 根据主键id删除课程视频
     *
     * @param id
     */
    @Override
    public boolean removeVideoById(String id) {
        Integer result = baseMapper.deleteById(id);
        return result!=null && result > 0;
    }
}
