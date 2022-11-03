package com.javaclimb.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.javaclimb.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.service.edu.entity.form.CourseInfoForm;
import com.javaclimb.service.edu.entity.vo.CoursePublishVo;
import com.javaclimb.service.edu.entity.vo.CourseQueryVo;
import com.javaclimb.service.edu.entity.vo.CourseVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *

 */
public interface CourseService extends IService<Course> {

    /**
     * 保存课程基本信息
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 根据id返回CourseInfoForm课程基本信息
     */
    CourseInfoForm getCourseInfoFormById(String id);

    /**
     * 修改课程基本信息
     */
    void updateCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 根据课程id获取课程发布信息
     */
    CoursePublishVo selectCoursePublishVoById(String id);

    /**
     * 发布课程
     */
    void publishCourseById(String id);

    /**
     * 分页查询课程列表
     * @param page             当前页
     * @param limit            每页记录数
     * @param courseQueryVo    查询条件对象
     * @return
     */
    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    /**
     * 根据id删除课程
     */
    boolean removeCourseById(String id);

    /**
     * 获取热门课程
     */
    List<Course> selectHotCourse();
}
