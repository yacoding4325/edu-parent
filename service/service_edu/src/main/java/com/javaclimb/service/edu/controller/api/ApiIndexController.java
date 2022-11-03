package com.javaclimb.service.edu.controller.api;

/**
 * @Author yaCoding
 * @create 2022-11-03 下午 9:29
 */

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.entity.Course;
import com.javaclimb.service.edu.entity.Teacher;
import com.javaclimb.service.edu.service.CourseService;
import com.javaclimb.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 为首页提供热门课程和名师数据
 */
@Api(description = "首页")
@RestController
@RequestMapping("/api/edu/index")
@Slf4j
@CrossOrigin
public class ApiIndexController {

    @Resource
    private CourseService courseService;

    @Resource
    private TeacherService teacherService;

    @ApiOperation("课程和讲师的首页数据")
    @GetMapping
    public R index() {
        //获取热门课程
        List<Course> courseList = courseService.selectHotCourse();
        //获取推荐讲师
        List<Teacher> teacherList = teacherService.selectHotTeacher();
        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
