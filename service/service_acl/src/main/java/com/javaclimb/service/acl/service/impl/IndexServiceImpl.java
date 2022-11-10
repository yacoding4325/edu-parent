package com.javaclimb.service.acl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.service.acl.entity.Role;
import com.javaclimb.service.acl.entity.User;
import com.javaclimb.service.acl.service.IndexService;
import com.javaclimb.service.acl.service.PermissionService;
import com.javaclimb.service.acl.service.RoleService;
import com.javaclimb.service.acl.service.UserService;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 9:12
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RedisTemplate redisTemplate;

    //根据用户名获取用户 登录信息
    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String,Object> result = new HashMap<>();
        User user = userService.selectByUsername(username);
        if (user == null) {
            throw new JavaclimbException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
        //根据用户id 获取角色
        List<Role> roleList = roleService.selectRoleByUserId(user.getId());
        //转换角色名称列表
        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        if (roleNameList.size() == 0) {
            //前端框架必须返回一个 角色  否则报错
            roleNameList.add("");
        }
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        redisTemplate.opsForValue().set(username,permissionValueList);

        result.put("name",user.getUsername());
        result.put("roles",roleNameList);
        result.put("permissionValueList",permissionValueList);
        return result;
    }

    //根据用户名 获取动态菜单
    @Override
    public List<JSONObject> getMenu(String username) {
        User user = userService.selectByUsername(username);
        if (user == null) {
            throw new JavaclimbException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
        //根据用户id 获取菜单权限
        List<JSONObject> permissionList = permissionService.selectPermissionByUserId(user.getId());
        return permissionList;
    }
}
