package com.offcn.context.service.impl;

import com.offcn.common.utils.Query;
import com.offcn.context.dao.CmsBannerDao;
import com.offcn.context.entity.CmsBannerEntity;
import com.offcn.context.service.CmsBannerService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.offcn.common.utils.PageUtils;


@Service("cmsBannerService")
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerDao, CmsBannerEntity> implements CmsBannerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CmsBannerEntity> page = this.page(
                new Query<CmsBannerEntity>().getPage(params),
                new QueryWrapper<CmsBannerEntity>()
        );

        return new PageUtils(page);
    }

}