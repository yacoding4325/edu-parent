package com.javaclimb.service.edu.service.impl;

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 3:55
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaclimb.service.edu.entity.Teacher;
import com.javaclimb.service.edu.entity.TeacherQueryVo;
import com.javaclimb.service.edu.mapper.TeacherMapper;
import com.javaclimb.service.edu.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * * <p>
 *  * 讲师 服务实现类
 *  * </p>
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    /**
     * 带查询条件的分页查询
     * @param pageParam       分页对象
     * @param teacherQueryVo  查询条件
     */
    @Override
    public IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        //排序，按照sort排序
        queryWrapper.orderByAsc("sort");
        //没有查询条件的情况下，直接返回分页查询的结果
        if(teacherQueryVo==null){
            return baseMapper.selectPage(pageParam,queryWrapper);
        }
        //获取查询条件
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();
        //填充查询条件
        //按照姓名模糊查询
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        //按照头衔查询
        if(level!=null){
            queryWrapper.eq("level",level);
        }
        //晚于讲师入驻开始时间
        if(!StringUtils.isEmpty(joinDateBegin)){
            queryWrapper.ge("join_date",joinDateBegin);
        }
        //早于讲师入驻结束时间
        if(!StringUtils.isEmpty(joinDateEnd)){
            queryWrapper.le("join_date",joinDateEnd);
        }
        //现在queryWrapper有很多条件
        return baseMapper.selectPage(pageParam,queryWrapper);
    }
}
