package com.offcn.context.controller;

import java.util.Arrays;
import java.util.Map;

import com.offcn.context.entity.CmsNewsEntity;
import com.offcn.context.service.CmsNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.R;



/**
 * 内容-资讯表
 *
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-10 17:03:56
 */
@RestController
@RequestMapping("context/news")
public class CmsNewsController {
    @Autowired
    private CmsNewsService cmsNewsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cmsNewsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("context:cmsnews:info")
    public R info(@PathVariable("id") Long id){
		CmsNewsEntity cmsNews = cmsNewsService.getById(id);

        return R.ok().put("cmsNews", cmsNews);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("context:cmsnews:save")
    public R save(@RequestBody CmsNewsEntity cmsNews){
		cmsNewsService.save(cmsNews);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("context:cmsnews:update")
    public R update(@RequestBody CmsNewsEntity cmsNews){
		cmsNewsService.updateById(cmsNews);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("context:cmsnews:delete")
    public R delete(@RequestBody Long[] ids){
		cmsNewsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
