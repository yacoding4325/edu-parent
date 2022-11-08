package com.javaclimb.service.edu.controller.api;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.entity.vo.SubjectVo;
import com.javaclimb.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-05 下午 7:20
 */
//课程分类 前台控制器
@Api(description = "课程分类")
@RestController
@RequestMapping("/api/edu/subject")
@Slf4j
//@CrossOrigin
public class ApiSubjectController {

    @Resource
    private SubjectService subjectService;

    @ApiOperation("嵌套数据列表")
    @GetMapping("/nested-list")
    public R nestedList(){
        List<SubjectVo> subjectVoList = subjectService.nestedList();
        return R.ok().data("items",subjectVoList);
    }

}
