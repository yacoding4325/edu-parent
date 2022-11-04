package com.javaclimb.service.base.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 12:49
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtInfo {
    private String id;
    private String nickname;
    private String avatar;
    //权限、角色等
    //不要存敏感信息
}