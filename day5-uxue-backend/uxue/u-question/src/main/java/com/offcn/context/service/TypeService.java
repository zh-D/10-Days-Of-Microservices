package com.offcn.context.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.offcn.common.utils.PageUtils;
import com.offcn.context.entity.TypeEntity;

import java.util.Map;

/**
 * 题目-题目类型表
 *
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-11 11:10:05
 */
public interface TypeService extends IService<TypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

