package com.javaclimb.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.base.utils.JwtInfo;
import com.javaclimb.service.base.utils.JwtUtils;
import com.javaclimb.service.edu.entity.Comment;
import com.javaclimb.service.edu.mapper.CommentMapper;
import com.javaclimb.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *

 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    /**
     * 前台评论列表分页查询
     * @param pageParam
     * @param courseId
     * @return
     */
    @Override
    public Map<String, Object> pageListWeb(Page<Comment> pageParam, String courseId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.selectPage(pageParam,queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("items",pageParam.getRecords());    //当前页的记录
        map.put("current",pageParam.getCurrent());    //当前页
        map.put("pages",pageParam.getPages());      //共有多少页
        map.put("size",pageParam.getSize());        //每页记录数
        map.put("total",pageParam.getTotal());      //总共记录数
        map.put("hasNext",pageParam.hasNext());     //是否有下一页
        map.put("hasPrevious",pageParam.hasPrevious());//是否有上一页
        return map;
    }

    /**
     * 添加评论
     * @param comment
     * @param request
     * @return
     */
    @Override
    public R save(Comment comment, HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (jwtInfo == null) {
            return R.error().code(ResultCodeEnum.LOGIN_AUTH.getCode()).message("请登录");
        }
        String memberId = jwtInfo.getId();
        if (memberId == null) {
            return R.error().code(ResultCodeEnum.LOGIN_AUTH.getCode()).message("请登录");
        }
        comment.setMemberId(memberId);
        comment.setAvatar(jwtInfo.getAvatar());
        comment.setNickname(jwtInfo.getNickname());
        baseMapper.insert(comment);
        return R.ok();
    }

}
