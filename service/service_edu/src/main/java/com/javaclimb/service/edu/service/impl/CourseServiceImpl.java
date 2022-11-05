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
import com.javaclimb.service.edu.entity.vo.CourseWebVo;
import com.javaclimb.service.edu.mapper.ChapterMapper;
import com.javaclimb.service.edu.mapper.CourseDescriptionMapper;
import com.javaclimb.service.edu.mapper.CourseMapper;
import com.javaclimb.service.edu.mapper.VideoMapper;
import com.javaclimb.service.edu.service.CourseDescriptionService;
import com.javaclimb.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaclimb.service.edu.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private VideoService videoService;

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
//        //根据courseId删除video（课时）
//        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
//        videoQueryWrapper.eq("course_id",id);
//        videoMapper.delete(videoQueryWrapper);
//        //删除chapter（章节）
//        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
//        chapterQueryWrapper.eq("course_id",id);
//        chapterMapper.delete(chapterQueryWrapper);
//        //删除description(课程详情)
//        courseDescriptionMapper.deleteById(id);
//        //最后删除课程
//        return this.removeById(id);

        //根据课程主键删除所有它下面的云端视频和课时
        videoService.removeByCourseId(id);
        //删除chapter（章节）
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        chapterMapper.delete(chapterQueryWrapper);
        //删除description(课程详情)
        courseDescriptionMapper.deleteById(id);
        //最后删除课程
        return this.removeById(id);
    }

    /**
     * 获取热门课程
     */
    @Cacheable(value = "index",key = "'selectHotCourse'")
    @Override
    public List<Course> selectHotCourse() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据讲师id查询他所讲的所有课程
     * @param teacherId
     * @return
     */
    @Override
    public List<Course> selectByTeacherId(String teacherId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherId);
        //按更新时间倒序排序
        queryWrapper.orderByDesc("gmt_modified");
        List<Course> list = baseMapper.selectList(queryWrapper);
        return list;
    }

    /**
     * 前台课程列表分页查询
     * @param pageParam
     * @param courseQueryVo
     * @return
     */
    @Override
    public Map<String, Object> pageListWeb(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseQueryVo.getSubjectParentId())){
            queryWrapper.eq("subject_parent_id",courseQueryVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseQueryVo.getSubjectId())){
            queryWrapper.eq("subject_id",courseQueryVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseQueryVo.getBuyCountSort())){
            queryWrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(courseQueryVo.getGmtCreateSort())){
            queryWrapper.orderByDesc("gmt_create");
        }
        if(!StringUtils.isEmpty(courseQueryVo.getPriceSort())){
            queryWrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageParam,queryWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("items",pageParam.getRecords());    //当前页的记录
        map.put("current",pageParam.getCurrent());    //当前页
        map.put("pages",pageParam.getPages());      //共有多少页
        map.put("size",pageParam.getSize());        //每页记录数
        map.put("total",pageParam.getTotal());      //总共记录数
        map.put("hasNext",pageParam.hasNext());     //是否有下一页
        map.put("hasPrevious",pageParam.hasPrevious());//是否有上一页
        return map;
    }

    /**
     *  根据课程id获取网站前台课程详情所需要的字段
     * @param id
     * @return
     */
    @Override
    public CourseWebVo selectInfoWebById(String id) {
        updatePageViewCount(id);
        return baseMapper.selectInfoWebById(id);

    }

    /**
     * 更新课程浏览数
     *
     * @param id
     */
    public void updatePageViewCount(String id) {
        Course course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }
}
