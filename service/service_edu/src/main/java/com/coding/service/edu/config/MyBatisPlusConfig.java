package com.coding.service.edu.config;

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 3:48
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MP配置类
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.coding.service.edu.mapper")
public class MyBatisPlusConfig {
}
