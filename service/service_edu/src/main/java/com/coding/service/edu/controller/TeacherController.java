package com.coding.service.edu.controller;

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 3:52
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coding.service.base.exception.CodingException;
import com.coding.service.base.result.R;
import com.coding.service.base.result.ResultCodeEnum;
import com.coding.service.edu.entity.Teacher;
import com.coding.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增讲师
     *
     */
    @ApiOperation(value = "新增讲师")
    @PostMapping("save")
    public R update(@ApiParam("讲师对象")@RequestBody Teacher teacher) {
        boolean result = teacherService.updateById(teacher);
        if(result){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("数据不存在或更新失败");
        }
    }

    /**
     * 根据id删除讲师   假删除
     */
    @ApiOperation(value = "根据ID删除讲师",notes = "逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "讲师ID",required = true)@PathVariable String id){
        boolean result = teacherService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    //异常测试
    @ApiOperation(value = "异常测试")
    @GetMapping("exceptionTest")
    public R exceptionTest() {
        try {
            int a = 10/0;
        } catch (Exception e) {
            throw new CodingException(ResultCodeEnum.DIVIDE_ZERO);
        }
        return  R.ok();
    }




}
