package com.offcn.context.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.offcn.common.utils.PageUtils;
import com.offcn.context.entity.CmsNewsEntity;

import java.util.Map;

/**
 * 内容-资讯表
 *
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-10 17:03:56
 */
public interface CmsNewsService extends IService<CmsNewsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

