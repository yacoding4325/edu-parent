package com.javaclimb.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.service.edu.entity.Subject;
import com.javaclimb.service.edu.entity.excel.ExcelSubjectData;
import com.javaclimb.service.edu.mapper.SubjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 读取上传的excel
 */
@Slf4j
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {

    //不能使用@AUtowired注入，因为这个类没有被Spring托管
    private SubjectMapper subjectMapper;

    public ExcelSubjectDataListener(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    /**
     * 遍历每一行数据
     * @param data  每一行数据
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext analysisContext) {
        //一级标题
        String levelOneTitle = data.getLevelOneTitle();
        //二级标题
        String levelTwoTitle = data.getLevelTwoTitle();
        //组装数据
        //判断一级标题是否存在
        Subject subjectLevelOne = getByTitle(levelOneTitle);
        String parentId = null;
        if(subjectLevelOne == null){        //不存在，新增
            //组装一级类别
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            //存入数据库
            subjectMapper.insert(subject);
            parentId = subject.getId();
        }else{
            parentId = subjectLevelOne.getId();
        }

        //将第二级类别存入数据库，也要防止数据重复
        Subject subByTitleLevelTwo = getSubByTitle(levelTwoTitle,parentId);
        if(subByTitleLevelTwo == null){
            //组装二级类别
            Subject subject = new Subject();
            subject.setParentId(parentId);
            subject.setTitle(levelTwoTitle);
            //存入数据库
            subjectMapper.insert(subject);
        }

    }

    /**
     * 所有数据读取之后的收尾工作
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部记录解析完成");
    }

    /**
     * 根据一级分类的名称查询数据是否存在
     */
    private Subject getByTitle(String title){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        queryWrapper.eq("parent_id","0");
        return subjectMapper.selectOne(queryWrapper);
    }

    /**
     * 根据二级分类的名称和父Id查询数据是否存在
     */
    private Subject getSubByTitle(String title,String parentId){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        queryWrapper.eq("parent_id",parentId);
        return subjectMapper.selectOne(queryWrapper);
    }
}
