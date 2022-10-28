package com.javaclimb.service.edu.controller.admin;

import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.edu.entity.vo.SubjectVo;
import com.javaclimb.service.edu.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程分类 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/edu/subject")
@CrossOrigin
public class SubjectController {
    @Resource
    private SubjectService subjectService;

    @ApiOperation("excel批量导入课程分类")
    @PostMapping("/import")
    public R batchImport(@ApiParam(value = "Excel文件",required = true) @RequestParam("file")MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            subjectService.batchImport(inputStream);
            return R.ok().message("批量导入成功");
        } catch (IOException e) {
            throw new JavaclimbException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation("嵌套数据列表")
    @GetMapping("/nested-list")
    public R nestedList(){
        List<SubjectVo> subjectVoList = subjectService.nestedList();
        return R.ok().data("items",subjectVoList);
    }
}

