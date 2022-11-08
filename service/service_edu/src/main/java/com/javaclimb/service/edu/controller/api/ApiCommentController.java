package com.javaclimb.service.edu.controller.api;

/**
 * @Author yaCoding
 * @create 2022-11-05 下午 7:57
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.entity.Comment;
import com.javaclimb.service.edu.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 前台课程评论相关页面
 */
@Api(description = "课程评论")
@RestController
@RequestMapping("/api/edu/comment")
@Slf4j
//@CrossOrigin
public class ApiCommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("根据课程id分页查询评论列表")
    @GetMapping("list/{page}/{limit}/{courseId}")
    public R list(@ApiParam(name="page",value = "当前页码",required = true)@PathVariable Long page,
                  @ApiParam(name="limit",value = "每页记录数",required = true)@PathVariable Long limit,
                  @ApiParam(name="courseId",value = "课程id",required = true)@PathVariable String courseId){
        Page<Comment> pageParam = new Page<>(page,limit);
        Map<String,Object> map = commentService.pageListWeb(pageParam,courseId);
        return R.ok().data(map);
    }

    @ApiOperation("添加评论")
    @PostMapping("save")
    public R save(@RequestBody Comment comment, HttpServletRequest request) {
        R r = commentService.save(comment,request);
        return r;
    }


}
