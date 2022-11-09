package com.javaclimb.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaclimb.service.acl.entity.User;
import com.javaclimb.service.acl.mapper.UserMapper;
import com.javaclimb.service.acl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author yaCoding
 * @create 2022-11-08 下午 6:16
 */
//用户表 实现实现类
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    /*根据用户名获取 用户详情*/
    @Override
    public User selectByUsername (String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username",username));
    }
}

