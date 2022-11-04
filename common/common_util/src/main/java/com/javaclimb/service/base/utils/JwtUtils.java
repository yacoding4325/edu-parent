package com.javaclimb.service.base.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
/**
 * @Author yaCoding
 * @create 2022-11-04 下午 12:50
 */

/**
 * JWT工具类
 */

public class JwtUtils {

    public static final String APP_SECRET = "sdfGRRD323FGSfdrtr4233";

    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary(APP_SECRET);
        return new SecretKeySpec(bytes, signatureAlgorithm.getJcaName());
    }

    /**
     * 获取 jwt token
     * @param jwtInfo 实体
     * @param expire 过期时间 m
     * @return 生成的 token
     */
    public static String getJwtToken(JwtInfo jwtInfo, int expire) {

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // 主题
                .setSubject("javaclimb-user")
                // 颁发时间
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                // 用户id
                .claim("id", jwtInfo.getId())
                // 用户昵称
                .claim("nickname", jwtInfo.getNickname())
                // 用户头像
                .claim("avatar", jwtInfo.getAvatar())
                .signWith(SignatureAlgorithm.HS256, getKeyInstance())
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkJwtToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param request
     * @return
     */
    public static boolean checkJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        return checkJwtToken(jwtToken);
    }

    /**
     * 根据token获取会员信息
     *
     * @param request
     * @return
     */
    public static JwtInfo getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        JwtInfo jwtInfo = new JwtInfo(claims.get("id").toString(), claims.get("nickname").toString(), claims.get(
                "avatar").toString());
        return jwtInfo;
    }
}
