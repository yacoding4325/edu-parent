package com.javaclimb.service.ucenter.service;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 12:59
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.service.ucenter.entity.Member;
import com.javaclimb.service.ucenter.entity.vo.LoginVo;
import com.javaclimb.service.ucenter.entity.vo.RegisterVo;

/**
 * 会员 表 服务类
 */
public interface MemberService extends IService<Member> {

    /**
     * 注册
     */
    void register(RegisterVo registerVo);

    /**
     * 会员登录
     */
    String login(LoginVo loginVo);

}
