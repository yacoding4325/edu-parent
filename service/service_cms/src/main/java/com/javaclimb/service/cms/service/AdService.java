package com.javaclimb.service.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.javaclimb.service.cms.entity.Ad;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.service.cms.entity.vo.AdVo;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *

 */
public interface AdService extends IService<Ad> {
    /**
     * 分页查询广告推荐列表
     */
    IPage<AdVo> selectPage(Long page,Long limit);

    /**
     * 删除云端图片
     * id: 当前图片地址对应的记录的主键
     */
    boolean removeAdImageById(String id);

    /**
     * 根据推荐位id显示广告推荐
     */
    List<Ad> selectByAdTypeId(String adTypeId);
}
