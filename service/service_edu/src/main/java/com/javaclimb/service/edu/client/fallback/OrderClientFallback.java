package com.javaclimb.service.edu.client.fallback;

import com.javaclimb.service.edu.client.OrderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author yaCoding
 * @create 2022-11-07 下午 9:29
 */

@Component
@Slf4j
public class OrderClientFallback implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberId, String courseId) {
        log.info("熔断保护");
        return false;
    }
}

