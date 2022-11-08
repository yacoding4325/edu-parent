package com.javaclimb.service.acl.controller.admin;

/**
 * @Author yaCoding
 * @create 2022-11-08 下午 4:52
 */

import com.javaclimb.service.acl.entity.Permission;
import com.javaclimb.service.acl.service.PermissionService;
import com.javaclimb.service.base.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** * 权限 前端控制器 */
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 递归获取全部菜单
     */
    @ApiOperation(value = "递归获取全部菜单")
    @GetMapping
    public R indexAllPermission(){
        List<Permission> list = permissionService.queryAllMenu();
        return R.ok().data("list",list);
    }
    /**
     * 新增菜单或功能
     */
    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public R save(@RequestBody Permission permission){
        permissionService.save(permission);
        return R.ok();
    }

    /**
     * 修改菜单或功能
     */
    @ApiOperation(value = "修改菜单或功能")
    @PutMapping("update")
    public R update(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return R.ok();
    }

    /**
     * 删除菜单，使用递归删除
     */
    @ApiOperation(value = "删除菜单，使用递归删除")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id){
        permissionService.removeChildById(id);
        return R.ok();
    }

    /**
     * 给角色分配菜单权限
     */
    @ApiOperation(value = "给角色分配菜单权限")
    @PostMapping("doAssign")
    public R doAssign(String roleId,String[] permissionIds){
        permissionService.saveRolePermissionRelationShip(roleId,permissionIds);
        return R.ok();
    }

    /**
     * 根据角色获取菜单
     */
    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public R toAssign(@PathVariable String roleId){
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return R.ok().data("list",list);
    }
}
