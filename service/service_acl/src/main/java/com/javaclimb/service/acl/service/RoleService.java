package com.javaclimb.service.acl.service;

import com.javaclimb.service.acl.entity.Permission;
import com.javaclimb.service.acl.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务类
 * </p>
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户获取角色.包含所有权限
     */
    Map<String,Object> findRoleByUserId(String userId);

    /**
     * 给用户分配角色
     */
    void saveUserRoleRelationShip(String userId, String[] roleIds);

    /**
     * 根据用户获取角色
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(String userId);
}
