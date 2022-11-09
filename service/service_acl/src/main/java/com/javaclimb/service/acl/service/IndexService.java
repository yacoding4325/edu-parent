package com.javaclimb.service.acl.service;

import java.util.Map;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 9:10
 */

public interface IndexService {
    /**
     * 根据用户名获取用户 登录信息
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);
}
