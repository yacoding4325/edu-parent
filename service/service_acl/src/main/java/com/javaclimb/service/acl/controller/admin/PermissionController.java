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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
