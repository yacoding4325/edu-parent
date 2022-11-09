package com.javaclimb.service.acl.service.impl;

import com.javaclimb.security.entity.SecurityUser;
import com.javaclimb.service.acl.entity.User;
import com.javaclimb.service.acl.service.PermissionService;
import com.javaclimb.service.acl.service.UserService;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.ResultCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 3:55
 */
//自定义UserDetailsService实现类，认证用户详情
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     *  根据用户名 查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库 中取出用户信息
        User user = userService.selectByUsername(username);
        if (user == null) {
            throw new JavaclimbException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }
        com.javaclimb.security.entity.User currentUser = new com.javaclimb.security.entity.User();
        BeanUtils.copyProperties(user,currentUser);
        //根据用户id查询 有权限的菜单
        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(currentUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }
}
