package com.javaclimb.service.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaclimb.service.base.dto.MemberDto;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.base.utils.FormUtils;
import com.javaclimb.service.base.utils.JwtInfo;
import com.javaclimb.service.base.utils.JwtUtils;
import com.javaclimb.service.base.utils.MD5;
import com.javaclimb.service.ucenter.entity.Member;
import com.javaclimb.service.ucenter.entity.vo.LoginVo;
import com.javaclimb.service.ucenter.entity.vo.RegisterVo;
import com.javaclimb.service.ucenter.mapper.MemberMapper;
import com.javaclimb.service.ucenter.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 12:59
 */

/** * 会员表 服务实现类 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    /**
     * 注册
     * @param registerVo
     */

    @Override
    public void register(RegisterVo registerVo) {
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String nickname = registerVo.getNickname();

        //如果手机号为空或者不是手机号的格式，直接抛出手机号错误异常
        if(StringUtils.isEmpty(mobile)||!FormUtils.isMobile(mobile)){
            throw new JavaclimbException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }
        //判断密码和昵称是否为空
        if(StringUtils.isEmpty(password)||StringUtils.isEmpty(nickname)){
            throw new JavaclimbException(ResultCodeEnum.PARAM_ERROR);
        }

        //判断手机号是否已被注册
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer res = baseMapper.selectCount(queryWrapper);
        if (res > 0) {
            throw new JavaclimbException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }
        //注册
        Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(nickname);
        //密码加密放到数据库
        member.setPassword(MD5.encrypt(password));
        member.setDisabled(false);
        member.setAvatar("https://javaclimb-file.oss-cn-beijing.aliyuncs.com/avatar/01.jpeg");
        baseMapper.insert(member);
    }

    /**
     * 会员登录
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //如果手机号为空或者不是手机号的格式，直接抛出手机号错误异常
        if (StringUtils.isEmpty(mobile) || FormUtils.isMobile(mobile)) {
            throw new JavaclimbException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }
        //判断，密码是否为空
        if (StringUtils.isEmpty(password)) {
            throw new JavaclimbException(ResultCodeEnum.PARAM_ERROR);
        }

        //判断手机号 是否被注册
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if (member == null) {
            throw new JavaclimbException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }

        //判断面是否正确
        if(!MD5.encrypt(password).equals((member.getPassword()))){
            throw new JavaclimbException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        //判断用户是否被禁用
        if (member.getDisabled()) {
            throw new JavaclimbException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }
        //登录 ：生成token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo,1800);
        return jwtToken;
    }

    @Override
    public Integer selectRegisterNumber(String day) {
        return baseMapper.selectRegisterNumber(day);
    }

    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        Member member = baseMapper.selectById(memberId);
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member,memberDto);
        return memberDto;
    }
}
