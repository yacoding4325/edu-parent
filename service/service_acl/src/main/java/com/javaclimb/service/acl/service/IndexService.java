package com.javaclimb.service.acl.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
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

    /**
     * 根据用户名获取 动态菜单
     * @param username
     * @return
     */
    List<JSONObject> getMenu(String username);
}
