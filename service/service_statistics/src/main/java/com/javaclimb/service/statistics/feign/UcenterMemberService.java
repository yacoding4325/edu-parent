package com.javaclimb.service.statistics.feign;

/**
 * @Author yaCoding
 * @create 2022-11-06 下午 11:36
 */

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.statistics.feign.fallback.UcenterMemberServiceFallBack;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用用户相关的微服务
 */
@FeignClient(value = "service-ucenter",fallback = UcenterMemberServiceFallBack.class)
public interface UcenterMemberService {
    @GetMapping("/admin/ucenter/member/count-register/{day}")
    public R countRegisterNum(@ApiParam(value = "统计日期",required = true)@PathVariable String day);
}
