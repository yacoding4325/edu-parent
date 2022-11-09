package com.javaclimb.service.acl.service;

import com.javaclimb.service.acl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 获取全部菜单
     * @return
     */
    List<Permission> queryAllMenu();

    /**
     * 递归删除菜单
     */
    void removeChildById(String id);

    /**
     * 根据角色获取菜单
     */
    List<Permission> selectAllMenu(String roleId);

    /**
     * 给角色分配菜单权限
     */
    void saveRolePermissionRelationShip(String roleId,String[] permissionIds);

    /**
     * 根据角色分配菜单权限
     * @param userId
     * @return
     */
    List<String> selectPermissionValueByUserId(String userId);
}
