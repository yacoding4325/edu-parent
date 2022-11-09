package com.javaclimb.service.acl.service;

import com.javaclimb.service.acl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名获取用户详情
     * @param username
     * @return
     */
    User selectByUsername(String username);

}
