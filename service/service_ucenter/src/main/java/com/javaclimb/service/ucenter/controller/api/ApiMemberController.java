package com.javaclimb.service.ucenter.controller.api;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 12:57
 */

import com.javaclimb.service.base.dto.MemberDto;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.base.utils.JwtInfo;
import com.javaclimb.service.base.utils.JwtUtils;
import com.javaclimb.service.ucenter.entity.vo.LoginVo;
import com.javaclimb.service.ucenter.entity.vo.RegisterVo;
import com.javaclimb.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 会员管理
 */
@Api(description = "会员管理")
@RestController
@RequestMapping("/api/ucenter/member")
@Slf4j
//@CrossOrigin
public class ApiMemberController {

    @Resource
    private MemberService memberService;

    @ApiOperation("会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok().message("注册成功");
    }

    @ApiOperation("会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().message("登录成功").data("token",token);
    }

    @ApiOperation("根据token获取会员信息")
    @GetMapping("get-login-info")
    public R getLoginInfo(HttpServletRequest request){
        try{
            JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
            return R.ok().data("userInfo",jwtInfo);
        }catch (Exception e){
            log.error("解析用户信息失败，"+e.getMessage());
            throw new JavaclimbException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

    @ApiOperation("根据会员id获取会员信息")
    @GetMapping("inner/get-member-dto/{memberId}")
    public MemberDto getMemberDtoByMemberId(@ApiParam(value = "会员id",required = true)@PathVariable String memberId){
        MemberDto memberDto = memberService.getMemberDtoByMemberId(memberId);
        return memberDto;
    }

}
