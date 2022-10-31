package com.javaclimb.service.vod.utils;

/**
 * @Author yaCoding
 * @create 2022-10-31 下午 8:12
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云视频点播常量类
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.vod")
public class VodProperties {
    private String KeyId;
    private String keySecret;
}
