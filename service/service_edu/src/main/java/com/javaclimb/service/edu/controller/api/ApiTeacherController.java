package com.javaclimb.service.edu.controller.api;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 3:56
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.entity.Course;
import com.javaclimb.service.edu.entity.Teacher;
import com.javaclimb.service.edu.service.CourseService;
import com.javaclimb.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 前页台名师相关面
 */
@Api(description = "名师")
@RestController
@RequestMapping("/api/edu/teacher")
@Slf4j
//@CrossOrigin
public class ApiTeacherController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;

    @ApiOperation("分页讲师列表")
    @GetMapping(value = "list/{page}/{limit}")
    public R pageList(@ApiParam(name = "page",value = "当前页码",required = true) @PathVariable Long page,
                      @ApiParam(name="limit",value = "每页记录数",required = true)@PathVariable Long limit) {
        Page<Teacher> pageParam = new Page<Teacher>(page,limit);
        Map<String, Object> map = teacherService.pageListWeb(pageParam);
        return R.ok().data(map);
    }

    //根据id获取讲师信息 带所讲课程列表
    @ApiOperation(value = "根据id获取讲师信息,带所讲课程列表")
    @GetMapping("get/{id}")
    public R getTeacherById(@ApiParam(value = "讲师ID",required = true)@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            List<Course> courseList = courseService.selectByTeacherId(id);
            return R.ok().data("item",teacher).data("courseList",courseList);
        }
        return R.error().message("数据不存在");
    }
}
