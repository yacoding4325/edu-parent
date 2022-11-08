package com.javaclimb.service.acl.service;

import com.javaclimb.service.acl.entity.Permission;

import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-08 下午 4:53
 */

public interface PermissionService {
    /**
     * 获取全部菜单
     * @return
     */
    List<Permission> queryAllMenu();

}
