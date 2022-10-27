package com.javaclimb.service.edu.controller.admin;

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 3:52
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.edu.entity.Teacher;
import com.javaclimb.service.edu.entity.TeacherQueryVo;
import com.javaclimb.service.edu.service.TeacherService;
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
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //查询讲师表所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }

    //根据id获取讲师信息
    @ApiOperation(value = "根据id获取讲师信息")
    @GetMapping("get/{id}")
    public R getTeacherById(@ApiParam(value = "讲师ID",required = true)@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        if(teacher!=null){
            return R.ok().data("item",teacher);
        }
        return R.error().message("数据不存在");
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "讲师分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码",required = true,example = "1") @PathVariable Long page,
                      @ApiParam(value = "每页记录数",required = true,example = "1") @PathVariable Long limit,
                      @ApiParam(value = "讲师查询对象") TeacherQueryVo teacherQueryVo){
        Page<Teacher> pageParam = new Page<>(page,limit);
        //teacherService.page(pageParam);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam,teacherQueryVo);
        //获取讲师列表
        //List<Teacher> records = pageParam.getRecords();
        List<Teacher> records = pageModel.getRecords();
        //获取讲师总数
        //long total = pageParam.getTotal();
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 新增讲师
     */
    @ApiOperation(value = "新增讲师")
    @PostMapping("save")
    public R save(@ApiParam("讲师对象")@RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok().message("保存成功");
    }

    /**
     * 更新讲师
     */
    @ApiOperation(value = "更新讲师")
    @PostMapping("update")
    public R update(@ApiParam("讲师对象")@RequestBody Teacher teacher){
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
    public R exceptionTest(){
        try {
            int a = 1/0;
        }catch (Exception e){
            throw new JavaclimbException(ResultCodeEnum.DIVIDE_ZERO);
        }
        return R.ok();
    }

    /**
     * 根据ID列表批量删除讲师
     */
    @ApiOperation(value = "根据ID列表批量删除讲师",notes = "逻辑批量删除")
    @DeleteMapping("batch-remove")
    public R batchRemove(@ApiParam(value = "讲师ID列表",required = true)@RequestBody List<String>idList){
        boolean res = teacherService.removeByIds(idList);
        if(res){
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }
}


