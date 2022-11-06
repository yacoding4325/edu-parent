package com.javaclimb.service.trade.feign.fallback;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.trade.feign.EduCourseService;
import com.javaclimb.service.base.dto.CourseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EduCourseServiceFallback implements EduCourseService {
    @Override
    public CourseDto selectCourseDtoById(String courseId) {
        log.info("熔断保护");
        return null;
    }

    @Override
    public R updateBuyById(String courseId) {
        log.info("熔断保护");
        return null;
    }
}
