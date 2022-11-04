package com.javaclimb.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 12:58
 */

/**
 * 注册VO
 */
@Data
public class RegisterVo implements Serializable {

    //手机号
    private String mobile;

    //密码
    private String password;

    //昵称
    private String nickname;

}
