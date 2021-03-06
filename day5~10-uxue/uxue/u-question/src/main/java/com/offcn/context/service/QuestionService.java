package com.offcn.context.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.offcn.common.utils.PageUtils;
import com.offcn.context.entity.QuestionEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-11 11:10:05
 */
public interface QuestionService extends IService<QuestionEntity> {

    PageUtils queryPage(Map<String, Object> params);
    //导入
    public Map importExcel(MultipartFile file);
    //导出
    public Workbook exportExcel();


    //统计题目数量
    List<Map<String,Object>> countTypeQuestion();
}
