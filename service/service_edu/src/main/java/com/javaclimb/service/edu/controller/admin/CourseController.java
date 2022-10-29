package com.javaclimb.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.entity.Course;
import com.javaclimb.service.edu.entity.form.CourseInfoForm;
import com.javaclimb.service.edu.entity.vo.CoursePublishVo;
import com.javaclimb.service.edu.entity.vo.CourseQueryVo;
import com.javaclimb.service.edu.entity.vo.CourseVo;
import com.javaclimb.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/edu/course")
@Api(description = "课程管理")
public class CourseController {

    @Resource
    private CourseService courseService;

    @ApiOperation("新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(@ApiParam(value = "课程基本信息",required = true)@RequestBody CourseInfoForm courseInfoForm){
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId",courseId).message("保存成功");
    }

    @ApiOperation("根据id查询课程")
    @GetMapping("course-info/{id}")
    public R getById(@ApiParam(value = "课程id",required = true)@PathVariable String id){
        CourseInfoForm courseInfoForm = courseService.getCourseInfoFormById(id);
        if(courseInfoForm!=null){
            return R.ok().data("item",courseInfoForm);
        }else{
            return R.error().message("数据不存在！");
        }
    }

    @ApiOperation("更新课程")
    @PutMapping("update-course-info")
    public R update(@ApiParam(value = "课程基本信息",required = true)@RequestBody CourseInfoForm courseInfoForm){
        courseService.updateCourseInfo(courseInfoForm);
        return R.ok().message(("修改成功"));
    }

    @ApiOperation("根据id获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R selectCoursePublishVoById(@ApiParam(value = "课程id",required = true)@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.selectCoursePublishVoById(id);
        if(coursePublishVo!=null){
            return R.ok().data("item",coursePublishVo);
        }else{
            return R.error().message("数据不存在！");
        }
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourse(@ApiParam(value = "课程id",required = true)@PathVariable String id){
        courseService.publishCourseById(id);
        return R.ok().message(("发布成功"));
    }

    @ApiOperation(value = "课程分页列表")
    @GetMapping("list/{page}/{limit}")
    public R list(@ApiParam(value="当前页码",required = true)@PathVariable Long page, @ApiParam(value="每页记录数",required = true)@PathVariable Long limit,
                  @ApiParam(value="课程列表查询条件对象",required = true)CourseQueryVo courseQueryVo){
        IPage<CourseVo> pageModel = courseService.selectPage(page,limit,courseQueryVo);
        List<CourseVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("根据id删除课程")
    @GetMapping("remove/{id}")
    public R removeById(@ApiParam(value = "课程id",required = true)@PathVariable String id){
        boolean res = courseService.removeCourseById(id);
        if(res){
            return R.ok().message("删除成功！");
        }else{
            return R.error().message("删除失败！");
        }
    }
}















