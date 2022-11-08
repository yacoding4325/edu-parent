package com.javaclimb.service.edu.controller.api;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 5:00
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.base.dto.CourseDto;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.utils.JwtInfo;
import com.javaclimb.service.base.utils.JwtUtils;
import com.javaclimb.service.edu.client.OrderClient;
import com.javaclimb.service.edu.entity.Course;
import com.javaclimb.service.edu.entity.vo.ChapterVo;
import com.javaclimb.service.edu.entity.vo.CourseQueryVo;
import com.javaclimb.service.edu.entity.vo.CourseWebVo;
import com.javaclimb.service.edu.service.ChapterService;
import com.javaclimb.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 前台课程相关页面
 */
@Api(description = "课程")
@RestController
@RequestMapping("api/edu/course")
@Slf4j
//@CrossOrigin
public class ApiCourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private ChapterService chapterService;

    @Resource
    private OrderClient orderClient;

    @ApiOperation("分页课程列表")
    @PostMapping(value = "list/{page}/{limit}")
    public R pageList(@ApiParam(name="page",value = "当前页码",required = true)@PathVariable Long page,
                      @ApiParam(name="limit",value = "每页记录数",required = true)@PathVariable Long limit,
                      @ApiParam(name="courseQueryVo",value = "查询对象",required = false)@RequestBody CourseQueryVo courseQueryVo) {
        Page<Course> pageParam = new Page<Course>(page,limit);
        Map<String,Object> map = courseService.pageListWeb(pageParam,courseQueryVo);
        return R.ok().data(map);
    }

//    @ApiOperation("根据课程id查询课程详情")
//    @GetMapping(value = "getById/{courseId}")
//    public R getById(@ApiParam(name="courseId",value = "课程id",required = true)@PathVariable String courseId) {
//        //根据课程id获取网站前台课程 详情所需的 字段
//        CourseWebVo courseWebVo =  courseService.selectInfoWebById(courseId);
//        //查询当前课程的章节信息
//        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
//        return R.ok().data("course",courseWebVo).data("chapterVoList",chapterVoList);
//    }

    @ApiOperation("根据课程id查询课程详情")
    @GetMapping(value = "getById/{courseId}")
    public R getById(@ApiParam(name="courseId",value = "课程id",required = true)@PathVariable String courseId, HttpServletRequest request){
        //根据课程id获取网站前台课程详情所需要的字段
        CourseWebVo courseWebVo =  courseService.selectInfoWebById(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        //远程调用，判断用户是否购买了该课程
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        boolean isbuy = orderClient.isBuyCourse(jwtInfo.getId(),courseId);
        return R.ok().data("course",courseWebVo).data("chapterVoList",chapterVoList).data("isbuy",isbuy);
    }

    @ApiOperation("根据课程id查询课程信息，给订单调用")
    @GetMapping(value = "inner/get-course-dto/{courseId}")
    public CourseDto selectCourseDtoById(@ApiParam(name="courseId",value = "课程id",required = true)@PathVariable String courseId){
        CourseDto courseDto =  courseService.selectCourseDtoById(courseId);
        return courseDto;
    }

    @ApiOperation("根据课程id更新销量")
    @GetMapping("inner/update-buy-count/{courseId}")
    public R updateBuyById(@ApiParam(name="courseId",value = "课程id",required = true)@PathVariable String courseId){
        courseService.updateBuyCountById(courseId);
        return R.ok();
    }



}
