package com.javaclimb.service.acl.controller.admin;

import com.javaclimb.service.acl.service.IndexService;
import com.javaclimb.service.base.result.R;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 8:02
 */
//动态菜单，获取用户信息
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    private IndexService indexService;

    /**
     * 获取用户信息
     */
    @GetMapping("info")
    public R getInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String,Object> userInfo = indexService.getUserInfo(username);
        return R.ok().data(userInfo);
    }

    /**
     * 获取菜单
     */
    @GetMapping("menu")
    public R getMenu() {
        return R.ok();
    }

    /**
     * 用户登录 退出
     */
    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }
}
