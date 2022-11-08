package com.javaclimb.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.service.acl.entity.*;
import com.javaclimb.service.acl.mapper.RoleMapper;
import com.javaclimb.service.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaclimb.service.acl.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @Author yaCoding
 * @create 2022-11-08 下午 5:56
 */

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 根据用户获取角色
     */
    @Override
    public Map<String,Object> findRoleByUserId(String userId) {
        //获取到所有角色
        List<Role> allRolesList = baseMapper.selectList(new QueryWrapper<>());
        //根据用户id获取到角色列表
        List<UserRole> existUserRoleList = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id",userId).select("role_id"));
        //遍历角色列表，只取roleId后放到新的list里面
        List<String> existRoleList = existUserRoleList.stream().map(c -> c.getRoleId()).collect(Collectors.toList());
        //获取到该用户的所有角色对象，放到list
        List<Role> assignRoles = new ArrayList<>();
        for (Role role:allRolesList) {
            if(existRoleList.contains(role.getId())){
                assignRoles.add(role);
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("assignRoles",assignRoles);
        map.put("allRolesList",allRolesList);
        return map;
    }

    /**
     * 给用户分配角色
     */
    @Override
    public void saveUserRoleRelationShip(String userId, String[] roleIds) {
        //删除用户的旧的所属角色
        userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id",userId));
        //组装要增加的角色列表
        List<UserRole> userRoleList = new ArrayList<>();
        for(String roleId:roleIds){
            if(StringUtils.isEmpty(roleId)){
                continue;
            }
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoleList.add(userRole);
        }
        //把角色权限列表批量保存到数据库
        userRoleService.saveBatch(userRoleList);
    }
}

