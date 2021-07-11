package com.offcn.context;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.offcn.context.entity.CmsBannerEntity;
import com.offcn.context.service.CmsBannerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UContextApplicationTests {

	@Autowired
	private CmsBannerService bannerService;


	@Test
	void testCreateBanner() {
		CmsBannerEntity bannerEntity = new CmsBannerEntity();
		bannerEntity.setTitle("测试广告");
		bannerEntity.setImgUrl("1.jpg");
		bannerEntity.setRenderUrl("http://www.ujiuye.com");
		//保存
		bannerService.save(bannerEntity);
	}

	@Test
	void testUpdate(){
		CmsBannerEntity bannerEntity = new CmsBannerEntity();
		bannerEntity.setId(1L);
		bannerEntity.setTitle("测试广告Update");
		bannerEntity.setImgUrl("888.jpg");
		bannerEntity.setRenderUrl("http://www.offcn.com");
		//更新
		bannerService.updateById(bannerEntity);
	}

	@Test
	void testFindOne(){
		QueryWrapper<CmsBannerEntity> queryWrapper = new QueryWrapper<CmsBannerEntity>().eq("id", 1L);

		List<CmsBannerEntity> list = bannerService.list(queryWrapper);
		list.forEach((item)->{
			System.out.println(item);
		});
		System.out.println("查询成功");
	}

	@Test
	void testDelete(){
		bannerService.removeById(1L);
		System.out.println("删除数据成功");
	}
}
