package com.coding.service.edu.controller;

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 3:52
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coding.service.base.result.R;
import com.coding.service.edu.entity.Teacher;
import com.coding.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  * <p>
 *  * 讲师 前端控制器
 *  * </p>
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //查询讲师表的所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<Teacher> list = teacherService.list();
        return R.ok().data("item",list);
    }

    //根据id获取讲师的信息
    @ApiOperation(value = "根据id获取讲师信息")
    @GetMapping("get/{id}")
    public R getTeacherById(@ApiParam("讲师ID")@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            return R.ok().data("item",teacher);
        }
        return R.error().message("数据不存在");
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "讲师分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码",required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数",required = true) @PathVariable Long limit){
        Page<Teacher> pageParam = new Page<>(page,limit);
        teacherService.page(pageParam);
        //获取讲师列表
        List<Teacher> records = pageParam.getRecords();
        //获取讲师总数
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }


}
