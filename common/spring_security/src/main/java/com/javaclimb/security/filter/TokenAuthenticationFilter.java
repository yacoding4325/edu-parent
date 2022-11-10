package com.javaclimb.security.filter;

import com.javaclimb.security.security.TokenManager;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 2:36
 */
//访问过滤器
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request.getRequestURI().indexOf("admin") == -1) {
            chain.doFilter(request,response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
            authenticationToken = getAuthentication(request);
        } catch (Exception e) {
            ResponseUtil.out(response, R.error());
        }
        if (authenticationToken == null) {
            ResponseUtil.out(response,R.error());
        } else {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token != null &&!"".equals((token.trim()))) {
            String userName = tokenManager.getUserFromToken(token);
            List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(userName);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String permissionValue: permissionValueList) {
                if (StringUtils.isEmpty(permissionValue)) {
                    continue;
                }
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
                authorities.add(authority);
            }
            return new UsernamePasswordAuthenticationToken(userName,token,authorities);
        }
        return null;

    }
}
