package com.javaclimb.service.edu.service;

import com.javaclimb.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.service.edu.entity.vo.SubjectVo;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *

 */
public interface SubjectService extends IService<Subject> {
    /**
     * 批量导入
     */
    void batchImport(InputStream inputStream);

    /**
     * 查询课程列表，返回树目录形式
     */
    List<SubjectVo> nestedList();
}
