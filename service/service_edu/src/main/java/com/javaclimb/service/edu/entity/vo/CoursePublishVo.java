package com.javaclimb.service.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 课程发布信息
 */
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo implements Serializable {
    /* 标题 */
    private String title;
    /* 封面 */
    private String cover;
    /* 总课时 */
    private Integer lessonNum;
    /* 一级分类 */
    private String subjectLevelOne;
    /* 二级分类 */
    private String subjectLevelTwo;
    /* 讲师姓名 */
    private String teacherName;
    /* 价格 */
    private String price;
}
