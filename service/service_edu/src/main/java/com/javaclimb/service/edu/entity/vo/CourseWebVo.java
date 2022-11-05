package com.javaclimb.service.edu.entity.vo;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 5:06
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * <p>
 * 网站课程详情页面需要的字段
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="课程对象", description="网站课程详情页面需要的字段")
public class CourseWebVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;

    @ApiModelProperty(value = "一级类别ID")
    private String subjectLevelOneId;

    @ApiModelProperty(value = "一级类别名称")
    private String subjectLevelOne;

    @ApiModelProperty(value = "二级类别ID")
    private String subjectLevelTwoId;

    @ApiModelProperty(value = "二级类别名称")
    private String subjectLevelTwo;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "讲师简介")
    private String intro;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

}
