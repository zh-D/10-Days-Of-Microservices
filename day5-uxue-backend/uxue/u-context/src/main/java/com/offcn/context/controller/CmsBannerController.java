package com.offcn.context.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offcn.context.entity.CmsBannerEntity;
import com.offcn.context.service.CmsBannerService;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.R;



/**
 * 内容-横幅广告表
 *
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-10 17:03:56
 */
@RestController
@RequestMapping("context/banner")
public class CmsBannerController {
    @Autowired
    private CmsBannerService cmsBannerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cmsBannerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("context:cmsbanner:info")
    public R info(@PathVariable("id") Long id){
		CmsBannerEntity cmsBanner = cmsBannerService.getById(id);

        return R.ok().put("cmsBanner", cmsBanner);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("context:cmsbanner:save")
    public R save(@RequestBody CmsBannerEntity cmsBanner){
		cmsBannerService.save(cmsBanner);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("context:cmsbanner:update")
    public R update(@RequestBody CmsBannerEntity cmsBanner){
		cmsBannerService.updateById(cmsBanner);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("context:cmsbanner:delete")
    public R delete(@RequestBody Long[] ids){
		cmsBannerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
