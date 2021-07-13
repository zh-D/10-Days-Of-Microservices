package com.offcn.context.dao;

import com.offcn.context.entity.TypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目-题目类型表
 * 
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-11 11:10:05
 */
@Mapper
public interface TypeDao extends BaseMapper<TypeEntity> {
	
}
