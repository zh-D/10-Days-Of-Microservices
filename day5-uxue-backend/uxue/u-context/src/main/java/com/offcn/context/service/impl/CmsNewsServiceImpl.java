package com.offcn.context.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.Query;

import com.offcn.context.dao.CmsNewsDao;
import com.offcn.context.entity.CmsNewsEntity;
import com.offcn.context.service.CmsNewsService;


@Service("cmsNewsService")
public class CmsNewsServiceImpl extends ServiceImpl<CmsNewsDao, CmsNewsEntity> implements CmsNewsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CmsNewsEntity> page = this.page(
                new Query<CmsNewsEntity>().getPage(params),
                new QueryWrapper<CmsNewsEntity>()
        );

        return new PageUtils(page);
    }

}