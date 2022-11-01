package com.javaclimb.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.edu.client.VodClient;
import com.javaclimb.service.edu.entity.Video;
import com.javaclimb.service.edu.entity.form.VideoInfoForm;
import com.javaclimb.service.edu.mapper.VideoMapper;
import com.javaclimb.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodClient vodClient;
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
        //查询云端视频 id
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除云端视频资源
        if (!StringUtils.isEmpty(videoSourceId)) {
            R r = vodClient.removeVideo(videoSourceId);
            if (!r.getSuccess()) {
                return false;
            }
        }
        Integer result = baseMapper.deleteById(id);
        return result!=null && result > 0;
    }

    /**
     * 根据课程主键删除所有它下面的云端视频和课时
     * @param courseId
     * @return
     */
    @Override
    public boolean removeByCourseId(String courseId) {
        //根据课程id 查询所有视频表
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.select("video_source_id");
        List<Video> videoList = baseMapper.selectList(queryWrapper);

        //得到所有视频列表的云端原始视频id
        List<String> videoSourceIdList = new ArrayList<>();
        for (Video video :videoList) {
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoSourceIdList.add(videoSourceId);
            }
        }
        //调用vod服务 删除云短视频
        if (videoSourceIdList.size() > 0) {
            vodClient.removeVideoList(videoSourceIdList);
        }
        //删除课时
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        baseMapper.delete(videoQueryWrapper);
        return true;
    }
}
