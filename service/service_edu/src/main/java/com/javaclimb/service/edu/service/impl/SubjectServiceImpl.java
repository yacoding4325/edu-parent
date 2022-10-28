package com.javaclimb.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.javaclimb.service.edu.entity.Subject;
import com.javaclimb.service.edu.entity.excel.ExcelSubjectData;
import com.javaclimb.service.edu.entity.vo.SubjectVo;
import com.javaclimb.service.edu.listener.ExcelSubjectDataListener;
import com.javaclimb.service.edu.mapper.SubjectMapper;
import com.javaclimb.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *

 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;

    /**
     * 批量导入
     *
     * @param inputStream
     */
    @Override
    public void batchImport(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelSubjectData.class, new ExcelSubjectDataListener(subjectMapper)).excelType(ExcelTypeEnum.XLS).sheet().doRead();
    }

    /**
     * 查询课程列表，返回树目录形式
     */
    @Override
    public List<SubjectVo> nestedList() {
        return subjectMapper.selectNestedListByParentId("0");
    }
}


















