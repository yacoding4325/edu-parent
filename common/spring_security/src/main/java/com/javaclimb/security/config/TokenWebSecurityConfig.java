package com.javaclimb.security.config;

import com.javaclimb.security.filter.TokenAuthenticationFilter;
import com.javaclimb.security.filter.TokenLoginFilter;
import com.javaclimb.security.security.DefaultPasswordEncoder;
import com.javaclimb.security.security.TokenLoginHandler;
import com.javaclimb.security.security.TokenManager;
import com.javaclimb.security.security.UnauthorizedEntryPoint;
import org.apache.ibatis.parsing.TokenHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 2:31
 */
//核心配置类
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private DefaultPasswordEncoder defaultPasswordEncoder;

    @Resource
    private TokenManager tokenManager;

    @Resource
    private RedisTemplate redisTemplate;

    /*密码处理*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /*配置设置*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint())//未授权登录异常
                .and().csrf().disable().authorizeRequests().anyRequest().authenticated() //取消跨域
                .and().logout().logoutUrl("/admin/acl/index/logout")
                .addLogoutHandler(new TokenLoginHandler(tokenManager,redisTemplate))
                .and().addFilter(new TokenLoginFilter(authenticationManager(),tokenManager,redisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(),tokenManager,redisTemplate))
                .httpBasic();
    }

    /*配置那些请求 不拦截*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**","/swagger-resources/**","/swagger-ui.html/**",
                "/webjars/**","/v2/**");
    }
}
