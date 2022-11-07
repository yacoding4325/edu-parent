package com.javaclimb.service.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author yaCoding
 * @create 2022-11-06 下午 11:24
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"com.javaclimb"})
@EnableScheduling           //定时任务
public class ServiceStatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceStatisticsApplication.class,args);
    }
}
