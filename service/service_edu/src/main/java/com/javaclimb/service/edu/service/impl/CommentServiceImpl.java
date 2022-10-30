package com.javaclimb.service.edu.service.impl;

import com.javaclimb.service.edu.entity.Comment;
import com.javaclimb.service.edu.mapper.CommentMapper;
import com.javaclimb.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *

 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
