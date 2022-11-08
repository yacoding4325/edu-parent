package com.javaclimb.service.cms.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.cms.entity.Ad;
import com.javaclimb.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 广告推荐 前端控制器
 * </p>

 */
@Api(description = "广告推荐管理")
@RestController
@RequestMapping("/admin/cms/ad")
@Slf4j
//@CrossOrigin
public class AdController {

    @Autowired
    private AdService adService;

    //查询广告位表所有数据
    @ApiOperation(value = "所有广告位列表")
    @GetMapping("findAll")
    public R findAllAd(){
        List<Ad> list = adService.list();
        return R.ok().data("items",list);
    }

    //根据id获取广告位信息
    @ApiOperation(value = "根据id获取广告位信息")
    @GetMapping("get/{id}")
    public R getAdById(@ApiParam(value = "广告位ID",required = true)@PathVariable String id){
        Ad ad = adService.getById(id);
        if(ad!=null){
            return R.ok().data("item",ad);
        }
        return R.error().message("数据不存在");
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "广告位分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码",required = true,example = "1") @PathVariable Long page,
                      @ApiParam(value = "每页记录数",required = true,example = "1") @PathVariable Long limit){
        Page<Ad> pageParam = new Page<>(page,limit);
        //adService.page(pageParam);
        IPage<Ad> pageModel = adService.page(pageParam);
        //获取广告位列表
        List<Ad> records = pageModel.getRecords();
        //获取广告位总数
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 新增广告位
     */
    @ApiOperation(value = "新增广告位")
    @PostMapping("save")
    public R save(@ApiParam("广告位对象")@RequestBody Ad ad){
        adService.save(ad);
        return R.ok().message("保存成功");
    }

    /**
     * 更新广告位
     */
    @ApiOperation(value = "更新广告位")
    @PostMapping("update")
    public R update(@ApiParam("广告位对象")@RequestBody Ad ad){
        boolean result = adService.updateById(ad);
        if(result){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("数据不存在或更新失败");
        }
    }

    /**
     * 根据id删除广告位
     */
    @ApiOperation(value = "根据ID删除广告位")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "广告位ID",required = true)@PathVariable String id){
        //删除图片
        adService.removeAdImageById(id);
        boolean result = adService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    /**
     * 根据ID列表批量删除广告位
     */
    @ApiOperation(value = "根据ID列表批量删除广告位")
    @DeleteMapping("batch-remove")
    public R batchRemove(@ApiParam(value = "广告位ID列表",required = true)@RequestBody List<String> idList){
        if(idList==null||idList.size()==0){
            return R.error().message("没有什么可删除的");
        }
        for(String id:idList){
            //删除图片
            adService.removeAdImageById(id);
        }
        boolean res = adService.removeByIds(idList);
        if(res){
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }
}

