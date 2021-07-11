package com.offcn.context.dao;

import com.offcn.context.entity.CmsNewsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 内容-资讯表
 * 
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-10 17:03:56
 */
@Mapper
public interface CmsNewsDao extends BaseMapper<CmsNewsEntity> {
	
}
