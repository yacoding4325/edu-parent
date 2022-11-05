package com.javaclimb.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.service.edu.entity.form.CourseInfoForm;
import com.javaclimb.service.edu.entity.vo.CoursePublishVo;
import com.javaclimb.service.edu.entity.vo.CourseQueryVo;
import com.javaclimb.service.edu.entity.vo.CourseVo;
import com.javaclimb.service.edu.entity.vo.CourseWebVo;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据讲师id查询他所讲的所有课程
     * @param teacherId
     * @return
     */
    List<Course> selectByTeacherId(String teacherId);

    /**
     * 前台课程列表分页查询
     */
    Map<String, Object> pageListWeb(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    /**
     * 根据课程id获取网站前台课程详情所需要的字段
     * @param id
     * @return
     */
    CourseWebVo selectInfoWebById(String id);

    /**
     * 更新课程浏览数
     * @param id
     */
   void updatePageViewCount(String id);

}
