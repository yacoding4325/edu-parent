package com.javaclimb.service.trade.feign.fallback;

import com.javaclimb.service.trade.feign.UcenterMemberService;
import com.javaclimb.service.base.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UcenterMemberServiceFallback implements UcenterMemberService {
    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        log.info("熔断保护");
        return null;
    }
}
