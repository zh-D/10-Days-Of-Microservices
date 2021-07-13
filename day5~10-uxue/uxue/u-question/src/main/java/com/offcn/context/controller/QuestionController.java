package com.offcn.context.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.offcn.context.entity.TypeEntity;
import com.offcn.context.service.TypeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.offcn.context.entity.QuestionEntity;
import com.offcn.context.service.QuestionService;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author sunny
 * @email 1641245614@qq.com
 * @date 2021-07-11 11:10:05
 */
@RestController
@RequestMapping("question/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private TypeService typeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("context:question:info")
    public R info(@PathVariable("id") Long id){
		QuestionEntity question = questionService.getById(id);

        return R.ok().put("question", question);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("context:question:save")
    public R save(@RequestBody QuestionEntity question){
		questionService.save(question);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("context:question:update")
    public R update(@RequestBody QuestionEntity question){
		questionService.updateById(question);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("context:question:delete")
    public R delete(@RequestBody Long[] ids){
		questionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    //题目上传导入
    @PostMapping("/upload")
    public R upload(MultipartFile file){
        Map result = questionService.importExcel(file);
        return R.ok().put("result",result.get("result")).put("msg",result.get("msg")).put("num",result.get("num"));
    }

    //导出excel
    @GetMapping("exportExcel")
    public void export(String tableName, HttpServletResponse response){
        System.out.println("导出excele");
        Workbook workbook = questionService.exportExcel();

        if (workbook != null) {
            String fileName = "uxue_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/octet-stream;charset=GB2312");
            OutputStream outputStream;
            try {
                outputStream = response.getOutputStream();
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.getWriter().print("error");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    //获取按照题库分类的统计数据
    @RequestMapping("countTypeQuestion")
    public R countTypeQuestion(){
        List<Map<String, Object>> mapList = questionService.countTypeQuestion();
        for (Map<String, Object> map : mapList) {

            //根据分类id，读取对应的分类数据
            Long typeId = (Long) map.get("TYPE");
            TypeEntity typeEntity = typeService.getById(typeId);
            //重新封装分类名称到map
            map.put("name",typeEntity.getType());
        }
        return R.ok().put("mapList",mapList);
    }
}
