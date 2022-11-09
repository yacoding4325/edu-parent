package com.javaclimb.security.security;

import com.javaclimb.service.base.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 2:43
 */
//密码处理类
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    /**加密密码* @return*/
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }


    /** 判断密码 是否正确*/
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
