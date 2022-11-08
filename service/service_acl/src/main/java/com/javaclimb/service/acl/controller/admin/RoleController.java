package com.javaclimb.service.acl.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.acl.entity.Role;
import com.javaclimb.service.acl.service.RoleService;
import com.javaclimb.service.base.result.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-08 下午 5:49
 */
//角色 前端控制器
@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //查询角色表所有数据
    @ApiOperation(value = "所有角色列表")
    @GetMapping("findAll")
    public R findAllRole(){
        List<Role> list = roleService.list();
        return R.ok().data("items",list);
    }

    //根据id获取角色信息
    @ApiOperation(value = "根据id获取角色信息")
    @GetMapping("get/{id}")
    public R getRoleById(@ApiParam(value = "角色ID",required = true)@PathVariable String id){
        Role role = roleService.getById(id);
        if(role!=null){
            return R.ok().data("item",role);
        }
        return R.error().message("数据不存在");
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "角色分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码",required = true,example = "1") @PathVariable Long page,
                      @ApiParam(value = "每页记录数",required = true,example = "1") @PathVariable Long limit,
                      @ApiParam(value = "角色查询对象") Role role){
        Page<Role> pageParam = new Page<>(page,limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(role.getRoleName())){
            wrapper.like("role_name",role.getRoleName());
        }
        roleService.page(pageParam,wrapper);
        return R.ok().data("total",pageParam.getTotal()).data("rows",pageParam.getRecords());
    }

    /**
     * 新增角色
     */
    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public R save(@ApiParam("角色对象")@RequestBody Role role){
        roleService.save(role);
        return R.ok().message("保存成功");
    }

    /**
     * 更新角色
     */
    @ApiOperation(value = "更新角色")
    @PostMapping("update")
    public R update(@ApiParam("角色对象")@RequestBody Role role){
        boolean result = roleService.updateById(role);
        if(result){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("数据不存在或更新失败");
        }
    }

    /**
     * 根据id删除角色   假删除
     */
    @ApiOperation(value = "根据ID删除角色",notes = "逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "角色ID",required = true)@PathVariable String id){
        boolean result = roleService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }
}
