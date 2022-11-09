package com.javaclimb.service.acl.controller.admin;

import com.javaclimb.service.base.result.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 8:02
 */
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    /**
     * 用户登录 退出
     */
    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }
}
