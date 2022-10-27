package com.javaclimb.service.edu.controller.admin;

import com.javaclimb.service.base.result.R;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author yaCoding
 * @create 2022-10-27 下午 9:43
 */
@RestController
@RequestMapping("admin/edu/user")
@CrossOrigin
public class LoginController {

    /**
     * 登录，获取到token
     */
    @PostMapping("/login")
    public R login() {
        return R.ok().data("token","admin");
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public R getInfo() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://javaclimb-file.oss-cn-beijing.aliyuncs.com/avatar/01.jpeg");
        return R.ok().data(map);
    }
}
