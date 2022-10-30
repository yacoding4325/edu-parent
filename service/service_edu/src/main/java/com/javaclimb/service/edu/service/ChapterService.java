package com.javaclimb.service.edu.service;

import com.javaclimb.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程章节 服务类
 * </p>
 *
 *
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 嵌套章节数据列表
     */
    List<ChapterVo> nestedList(String courseId);

    /**
     * 根据章节id删除
     */
    boolean removeChapterById(String id);
}
