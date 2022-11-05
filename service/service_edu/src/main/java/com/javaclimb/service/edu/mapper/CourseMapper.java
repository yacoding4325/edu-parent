package com.javaclimb.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.service.edu.entity.vo.CoursePublishVo;
import com.javaclimb.service.edu.entity.vo.CourseVo;
import com.javaclimb.service.edu.entity.vo.CourseWebVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 */
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 根据课程id获取课程发布信息
     */
    CoursePublishVo selectCoursePublishVoById(String id);

    /**
     * 分页查询课程列表
     * mp会自动组装queryMapper
     * @Param(Constants.WRAPPER)和xml文件中的${ew.customSqlSegment}对应
     *
     * @param pageParam         分页对象
     * @param queryWrapper      查询条件对象
     * @return
     */
    List<CourseVo> selectPageByCourseQueryVo(Page<CourseVo> pageParam, @Param(Constants.WRAPPER)QueryWrapper<CourseVo> queryWrapper);

    /**
     * 根据课程id获取网站前台课程详情所需要的字段
     * @param id
     * @return
     */
    CourseWebVo selectInfoWebById(String id);
}
