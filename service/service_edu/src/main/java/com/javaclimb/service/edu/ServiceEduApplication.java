package com.javaclimb.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 3:44
 */
@SpringBootApplication
@ComponentScan({"com.javaclimb"})
@EnableDiscoveryClient
public class ServiceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class,args);
    }

}
