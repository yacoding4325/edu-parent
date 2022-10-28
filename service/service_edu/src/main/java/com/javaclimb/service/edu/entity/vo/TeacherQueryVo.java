package com.javaclimb.service.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * 讲师 查询对象
 */
@ApiModel("讲师 查询对象")
@Data
public class TeacherQueryVo {

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师级别",example = "1")
    private Integer level;

    @ApiModelProperty(value = "入驻开始时间")
    private String joinDateBegin;

    @ApiModelProperty(value = "入驻结束时间")
    private String joinDateEnd;

}
