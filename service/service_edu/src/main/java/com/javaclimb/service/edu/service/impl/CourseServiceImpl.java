package com.javaclimb.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.edu.entity.Chapter;
import com.javaclimb.service.edu.entity.Course;
import com.javaclimb.service.edu.entity.CourseDescription;
import com.javaclimb.service.edu.entity.Video;
import com.javaclimb.service.edu.entity.form.CourseInfoForm;
import com.javaclimb.service.edu.entity.vo.CoursePublishVo;
import com.javaclimb.service.edu.entity.vo.CourseQueryVo;
import com.javaclimb.service.edu.entity.vo.CourseVo;
import com.javaclimb.service.edu.mapper.ChapterMapper;
import com.javaclimb.service.edu.mapper.CourseDescriptionMapper;
import com.javaclimb.service.edu.mapper.CourseMapper;
import com.javaclimb.service.edu.mapper.VideoMapper;
import com.javaclimb.service.edu.service.CourseDescriptionService;
import com.javaclimb.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>

 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private ChapterMapper chapterMapper;

    @Resource
    private CourseDescriptionMapper courseDescriptionMapper;

    /**
     * 保存课程基本信息
     *
     * @param courseInfoForm
     */
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        Course course = new Course();
        // courseInfoForm里面的字段必须与course里面的字段相同才能赋值
        BeanUtils.copyProperties(courseInfoForm,course);
        course.setStatus(Course.COUSE_DRAFT);
        baseMapper.insert(course);

        // 保存课程简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionService.save(courseDescription);

        return course.getId();
    }

    /**
     * 根据id返回CourseInfoForm课程基本信息
     *
     * @param id
     */
    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {
        //根据id获取Course
        Course course = baseMapper.selectById(id);
        if(course == null) {
            return null;
        }
        //根据id获取CourseDescription
        CourseDescription courseDescription = courseDescriptionService.getById(id);
        //组装成CourseInfoForm
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course,courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    /**
     * 修改课程基本信息
     *
     * @param courseInfoForm
     */
    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        //更新Course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        baseMapper.updateById(course);

        //更新CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseInfoForm.getId());
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }

    /**
     * 根据课程id获取课程发布信息
     */
    @Override
    public CoursePublishVo selectCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    /**
     * 发布课程
     *
     * @param id
     */
    @Override
    public void publishCourseById(String id) {
        Course course = baseMapper.selectById(id);
        course.setStatus(Course.COURSE_NORMAL);
        baseMapper.updateById(course);
    }

    /**
     * 分页查询课程列表
     *
     * @param page          当前页
     * @param limit         每页记录数
     * @param courseQueryVo 查询条件对象
     * @return
     */
    @Override
    public IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo) {
        //组装查询条件
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");
        String title = courseQueryVo.getTitle();
        String teacherId = courseQueryVo.getTeacherId();
        String SubjectParentId = courseQueryVo.getSubjectParentId();
        String SubjectId = courseQueryVo.getSubjectId();

        if(!StringUtils.isEmpty(title)){
            queryWrapper.like("c.title",title);
        }

        if(!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("c.teacher_id",teacherId);
        }

        if(!StringUtils.isEmpty(SubjectParentId)){
            queryWrapper.eq("c.subject_parent_id",SubjectParentId);
        }

        if(!StringUtils.isEmpty(SubjectId)){
            queryWrapper.eq("c.subject_id",SubjectParentId);
        }

        //组装分页
        Page<CourseVo> pageParam = new Page<>(page,limit);
        //只需要在mapper层传入封装好的分页组件即可，sql分页条件组装过程由mp自动完成
        List<CourseVo> records = baseMapper.selectPageByCourseQueryVo(pageParam,queryWrapper);
        // 将查询结果设置到pageParam中
        pageParam.setRecords(records);
        return pageParam;
    }

    /**
     * 根据id删除课程
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeCourseById(String id) {
        //根据courseId删除video（课时）
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        videoMapper.delete(videoQueryWrapper);
        //删除chapter（章节）
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        chapterMapper.delete(chapterQueryWrapper);
        //删除description(课程详情)
        courseDescriptionMapper.deleteById(id);
        //最后删除课程
        return this.removeById(id);
    }
}


















