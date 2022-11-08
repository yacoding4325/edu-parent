package com.javaclimb.service.acl.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.acl.entity.User;
import com.javaclimb.service.acl.service.RoleService;
import com.javaclimb.service.acl.service.UserService;
import com.javaclimb.service.base.result.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author yaCoding
 * @create 2022-11-08 下午 6:14
 */
//用户表 前端控制器
@RestController
@RequestMapping("/admin/acl/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //查询用户表所有数据
    @ApiOperation(value = "所有用户列表")
    @GetMapping("findAll")
    public R findAllUser(){
        List<User> list = userService.list();
        return R.ok().data("items",list);
    }

    //根据id获取用户信息
    @ApiOperation(value = "根据id获取用户信息")
    @GetMapping("get/{id}")
    public R getUserById(@ApiParam(value = "用户ID",required = true)@PathVariable String id){
        User user = userService.getById(id);
        if(user!=null){
            return R.ok().data("item",user);
        }
        return R.error().message("数据不存在");
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "用户分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码",required = true,example = "1") @PathVariable Long page,
                      @ApiParam(value = "每页记录数",required = true,example = "1") @PathVariable Long limit,
                      @ApiParam(value = "用户查询对象") User user){
        Page<User> pageParam = new Page<>(page,limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(user.getUsername())){
            wrapper.like("username",user.getUsername());
        }
        userService.page(pageParam,wrapper);
        return R.ok().data("total",pageParam.getTotal()).data("rows",pageParam.getRecords());
    }

    /**
     * 新增用户
     */
    @ApiOperation(value = "新增用户")
    @PostMapping("save")
    public R save(@ApiParam("用户对象")@RequestBody User user){
        userService.save(user);
        return R.ok().message("保存成功");
    }

    /**
     * 更新用户
     */
    @ApiOperation(value = "更新用户")
    @PostMapping("update")
    public R update(@ApiParam("用户对象")@RequestBody User user){
        boolean result = userService.updateById(user);
        if(result){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("数据不存在或更新失败");
        }
    }

    /**
     * 根据id删除用户   假删除
     */
    @ApiOperation(value = "根据ID删除用户",notes = "逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "用户ID",required = true)@PathVariable String id){
        boolean result = userService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    /**
     * 根据用户获取角色
     */
    @ApiOperation(value = "根据用户获取角色")
    @GetMapping("toAssign/{userId}")
    public R toAssign(@PathVariable String userId){
        Map<String,Object> map = roleService.findRoleByUserId(userId);
        return R.ok().data(map);
    }

    /**
     * 给用户分配角色
     */
    @ApiOperation(value = "给用户分配角色")
    @PostMapping("doAssign")
    public R doAssign(String userId,String[] roleIds){
        roleService.saveUserRoleRelationShip(userId,roleIds);
        return R.ok();
    }
}
