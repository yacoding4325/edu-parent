package com.javaclimb.service.trade.feign;

import com.javaclimb.service.trade.feign.fallback.UcenterMemberServiceFallback;
import com.javaclimb.service.base.dto.MemberDto;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 调用用户相关的远程接口
 */
@FeignClient(value = "service-ucenter",fallback = UcenterMemberServiceFallback.class)
public interface UcenterMemberService {

    /**
     * 根据会员id获取会员信息
     * @param memberId
     * @return
     */
    @GetMapping("/api/ucenter/member/inner/get-member-dto/{memberId}")
    public MemberDto getMemberDtoByMemberId(@ApiParam(value = "会员id",required = true)@PathVariable String memberId);
}















