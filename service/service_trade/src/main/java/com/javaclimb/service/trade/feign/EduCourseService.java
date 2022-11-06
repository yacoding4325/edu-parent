package com.javaclimb.service.trade.feign;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.trade.feign.fallback.EduCourseServiceFallback;
import com.javaclimb.service.base.dto.CourseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 调用课程相关的远程接口
 */
@FeignClient(value = "service-edu",fallback = EduCourseServiceFallback.class)
public interface EduCourseService {

    /**
     * 根据课程id查询课程信息，给订单调用
     * @param courseId
     * @return
     */
    @GetMapping(value = "/api/edu/course/inner/get-course-dto/{courseId}")
    public CourseDto selectCourseDtoById(@PathVariable String courseId);

    @ApiOperation("根据课程id更新销量")
    @GetMapping("/api/edu/course/inner/update-buy-count/{courseId}")
    public R updateBuyById(@ApiParam(name="courseId",value = "课程id",required = true)@PathVariable String courseId);
}
















