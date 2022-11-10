package com.javaclimb.service.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.service.acl.entity.Permission;

import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-08 下午 4:53
 */
//权限 Mapper 接口
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 获取全部菜单的权限值
     * @return
     */
    List<String> selectAllPermissionValue();

    /**
     * 根据用户id查询有权限的菜单
     * @param userId
     * @return
     */
    List<String> selectPermissionValueByUserId(String userId);

    /**
     * 根据用户id查询有权限的菜单 详情列表
     */
    List<Permission> selectPermissionByUserId(String userId);
}
