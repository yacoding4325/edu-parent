package com.javaclimb.service.oss.utils;

/**
 * @Author yaCoding
 * @create 2022-10-28 下午 5:09
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 从配置文件读取变量
 */

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {

    private String endpoint;
    private String KeyId;
    private String keySecret;
    private String bucketName;

}
