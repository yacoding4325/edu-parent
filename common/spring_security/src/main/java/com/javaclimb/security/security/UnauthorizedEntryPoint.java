package com.javaclimb.security.security;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 2:49
 */
//未授权的统一处理方式
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
            throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }

}
