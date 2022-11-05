package com.javaclimb.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *

 */
public interface CommentService extends IService<Comment> {

    /**
     * 前台评论列表分页查询
     */
    Map<String, Object> pageListWeb(Page<Comment> pageParam, String courseId);

    /**
     * /**
     *      添加评论
     */

    public R save(Comment comment, HttpServletRequest request);

}
