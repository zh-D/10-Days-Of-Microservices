package com.offcn.context.dao;

import com.offcn.context.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员-会员表
 * 
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-11 11:03:31
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
