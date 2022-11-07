package com.javaclimb.service.edu.client;

import com.javaclimb.service.edu.client.fallback.OrderClientFallback;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author yaCoding
 * @create 2022-11-07 下午 9:27
 */

@FeignClient(name = "service-trade",fallback = OrderClientFallback.class)
@Component
public interface OrderClient {

    @ApiOperation("用户是否购买了该课程")
    @GetMapping("/api/trade/order/isBuyCourse/{memberId}/{courseId}")
    public boolean isBuyCourse(@PathVariable String memberId, @PathVariable String courseId);
}
