package com.offcn.context.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.offcn.common.utils.PageUtils;
import com.offcn.context.entity.MemberEntity;

import java.util.Map;

/**
 * 会员-会员表
 *
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-11 11:03:31
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public MemberEntity login(String username,String password);
}

