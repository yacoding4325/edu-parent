package com.javaclimb.service.ucenter.entity.vo;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 12:58
 */

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录VO
 */
@Data
public class LoginVo implements Serializable {

    //手机号
    private String mobile;

    //密码
    private String password;

}
