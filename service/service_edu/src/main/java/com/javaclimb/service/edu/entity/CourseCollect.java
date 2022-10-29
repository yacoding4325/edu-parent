package com.javaclimb.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程收藏
 * </p>
 *

 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_course_collect")
@ApiModel(value="CourseCollect对象", description="课程收藏")
public class CourseCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收藏ID")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String courseId;

    @ApiModelProperty(value = "课程专业ID")
    private String memberId;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
