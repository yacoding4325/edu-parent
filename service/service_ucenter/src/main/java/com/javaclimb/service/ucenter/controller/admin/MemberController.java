package com.javaclimb.service.ucenter.controller.admin;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 12:57
 */

/**
 *  * 会员表 前端控制器
 */
@Api(description = "会员管理")
@RestController
@RequestMapping("/admin/ucenter/member")
@Slf4j
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("count-register/{day}")
    public R countRegisterNum(@ApiParam(value = "统计日期",required = true)@PathVariable String day){
        Integer number = memberService.selectRegisterNumber(day);
        return R.ok().data("registerNum",number);
    }
}
