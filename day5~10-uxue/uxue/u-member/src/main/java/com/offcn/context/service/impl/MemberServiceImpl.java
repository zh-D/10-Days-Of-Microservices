package com.offcn.context.service.impl;

import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.Query;
import com.offcn.context.dao.MemberDao;
import com.offcn.context.entity.MemberEntity;
import com.offcn.context.service.MemberService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public MemberEntity login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        MemberEntity memberEntity = this.getOne(new QueryWrapper<MemberEntity>().eq("user_name", username).eq("PASSWORD", password));
        return memberEntity;
    }

}