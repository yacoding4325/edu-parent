package com.javaclimb.service.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author yaCoding
 * @create 2022-11-06 下午 11:56
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"com.javaclimb"})
public class ServiceTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTradeApplication.class,args);
    }
}

