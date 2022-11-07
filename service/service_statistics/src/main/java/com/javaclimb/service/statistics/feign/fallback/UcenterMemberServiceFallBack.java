package com.javaclimb.service.statistics.feign.fallback;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.statistics.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author yaCoding
 * @create 2022-11-06 下午 11:36
 */
//熔断类
@Service
@Slf4j
public class UcenterMemberServiceFallBack implements UcenterMemberService {
    @Override
    public R countRegisterNum(String day) {
        log.info("熔断被执行");
        return R.ok().data("registerNum",0);
    }
}
