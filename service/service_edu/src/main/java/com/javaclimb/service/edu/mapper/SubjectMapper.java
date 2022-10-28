package com.javaclimb.service.edu.mapper;

import com.javaclimb.service.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.service.edu.entity.vo.SubjectVo;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    /**
     * 级联查询
     */
    List<SubjectVo> selectNestedListByParentId(String parentId);
}
