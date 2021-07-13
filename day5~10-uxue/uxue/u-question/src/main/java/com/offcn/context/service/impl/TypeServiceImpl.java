package com.offcn.context.service.impl;

import com.alibaba.nacos.api.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.Query;

import com.offcn.context.dao.TypeDao;
import com.offcn.context.entity.TypeEntity;
import com.offcn.context.service.TypeService;


@Service("typeService")
public class TypeServiceImpl extends ServiceImpl<TypeDao, TypeEntity> implements TypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //1、获取查询关键字
        String key= (String) params.get("key");
        //2、创建查询条件对象
        QueryWrapper<TypeEntity> queryWrapper = new QueryWrapper<>();
        //3、设置查询条件
        if(!StringUtils.isEmpty(key)){
            queryWrapper.eq("id",key).or().like("type",key);

        }
        IPage<TypeEntity> page = this.page(
                new Query<TypeEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<TypeEntity> findAll() {
        return this.list();
    }

}