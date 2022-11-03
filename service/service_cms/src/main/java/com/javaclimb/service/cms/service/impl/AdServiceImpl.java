package com.javaclimb.service.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.cms.entity.Ad;
import com.javaclimb.service.cms.entity.vo.AdVo;
import com.javaclimb.service.cms.feign.OssRemoveFileService;
import com.javaclimb.service.cms.mapper.AdMapper;
import com.javaclimb.service.cms.service.AdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    private OssRemoveFileService ossRemoveFileService;

    /**
     * 分页查询广告推荐列表
     *
     * @param page
     * @param limit
     */
    @Override
    public IPage<AdVo> selectPage(Long page, Long limit) {
        QueryWrapper<AdVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("a.sort");
        Page<AdVo> pageParam = new Page<>(page,limit);
        List<AdVo> records = baseMapper.selectPageByQueryWrapper(pageParam,queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }

    /**
     * 删除云端图片
     * id: 当前图片地址对应的记录的主键
     */
    @Override
    public boolean removeAdImageById(String id) {
        Ad ad = baseMapper.selectById(id);
        if(ad == null){
            return false;
        }
        String imageUrl = ad.getImageUrl();
        if(!StringUtils.isEmpty(imageUrl)){
            //删除图片
            R r = ossRemoveFileService.removeFile(imageUrl);
            return r.getSuccess();
        }
        return false;
    }

    /**
     * 根据推荐位id显示广告推荐
     * 1、查询redis缓存中是否存在需要的数据hasKey
     * 2、如果不存在，执行方法，将返回值返回并且存到redis中 set
     * 3、如果存在，则从缓存中读取数据 get
     * @param adTypeId
     */
    @Cacheable(value = "index",key = "'selectByAdTypeId'")
    @Override
    public List<Ad> selectByAdTypeId(String adTypeId) {
        QueryWrapper<Ad> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.eq("type_id",adTypeId);
        return baseMapper.selectList(queryWrapper);
    }
}