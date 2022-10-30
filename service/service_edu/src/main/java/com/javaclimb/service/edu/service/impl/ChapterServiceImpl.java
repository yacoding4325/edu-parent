package com.javaclimb.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.edu.entity.Chapter;
import com.javaclimb.service.edu.entity.Video;
import com.javaclimb.service.edu.entity.vo.ChapterVo;
import com.javaclimb.service.edu.entity.vo.VideoVo;
import com.javaclimb.service.edu.mapper.ChapterMapper;
import com.javaclimb.service.edu.mapper.VideoMapper;
import com.javaclimb.service.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaclimb.service.edu.service.VideoService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程章节 服务实现类
 * </p>
 *
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private VideoService videoService;

    /**
     * 嵌套章节数据列表
     *
     * @param courseId
     */
    @Override
    public List<ChapterVo> nestedList(String courseId) {
        //获取章节列表
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        chapterQueryWrapper.orderByAsc("sort","id");
        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);

        //获取课时列表
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        videoQueryWrapper.orderByAsc("sort","id");
        List<Video> videoList = videoMapper.selectList(videoQueryWrapper);

        //组装章节列表
        List<ChapterVo> chapterVoList = new ArrayList<>();
        for(Chapter chapter:chapterList){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVoList.add(chapterVo);
            //组装VideoVo
            List<VideoVo> videoVoList = new ArrayList<>();
            for(Video video:videoList){
                if(chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }

        return chapterVoList;
    }

    /**
     * 根据章节id删除
     *
     * @param id
     */
    @Override
    public boolean removeChapterById(String id) {
        //根据id判断是否存在视频，如果存在，则提示用户尚有子节点
        if(videoService.getCountByChapterId(id)){
            throw new JavaclimbException(ResultCodeEnum.HAS_CHILDREN);
        }
        Integer result = baseMapper.deleteById(id);
        return result!=null&& result>0;
    }
}
