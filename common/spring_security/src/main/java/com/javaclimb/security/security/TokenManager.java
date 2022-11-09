package com.javaclimb.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 2:48
 */
// token操作工具类
@Component
public class TokenManager {

    //设置一个token 的过期时间
    private long tokenExpiration = 24*60*60*1000;

    //私钥
    private String tokenSignKey = "123456";


    /** * 创建token*/
    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS256,tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /*** 根据token获取用户信息*/
    public String getUserFromToken(String token) {
        String username = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return username;
    }

    /** 删除toke*/
    public void removeToken(String token) {
        //jwt token 无需删除 客户端扔掉即可
    }

}
