package com.javaclimb.service.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaclimb.service.acl.entity.UserRole;
import com.javaclimb.service.acl.mapper.UserRoleMapper;
import com.javaclimb.service.acl.service.UserRoleService;
import com.javaclimb.service.acl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author yaCoding
 * @create 2022-11-08 下午 6:04
 */
//用户角色关联表 服务实现类
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

