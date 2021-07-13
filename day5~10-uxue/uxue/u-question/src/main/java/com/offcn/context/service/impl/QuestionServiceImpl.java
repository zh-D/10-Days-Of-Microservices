package com.offcn.context.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.offcn.common.utils.PageUtils;
import com.offcn.common.utils.Query;

import com.offcn.context.dao.QuestionDao;
import com.offcn.context.entity.QuestionEntity;
import com.offcn.context.service.QuestionService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service("questionService")
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, QuestionEntity> implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //获取查询关键字
        String key = (String) params.get("key");
        //创建查询条件对象
        QueryWrapper<QuestionEntity> queryWrapper = new QueryWrapper<>();
        //设置查询条件
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key).or().like("title", key);
        }

        IPage<QuestionEntity> page = this.page(
                new Query<QuestionEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public Map importExcel(MultipartFile file) {
        Map result = new HashMap();

        //获取上传文件名称
        String filename = file.getOriginalFilename();
        //判断文件后缀名
        if (!filename.matches("^.+\\\\.(?i)(xls)$") && !filename.matches("^.+\\\\.(?i)(xlsx)$")) {
            result.put("result", false);
            result.put("msg", "文件扩展名不正确，导入失败");
            result.put("num", 0);
        }

        //创建一个集合，存储要导入的题目对象
        List<QuestionEntity> list = new ArrayList<>();

        try {
            InputStream inputStream = file.getInputStream();
            //初始化创建工作簿对象
            Workbook workbook = WorkbookFactory.create(inputStream);
            //获取第一张工作表
            Sheet sheet = workbook.getSheetAt(0);
            //获取工作表中的总行数
            int rowsum = sheet.getPhysicalNumberOfRows();

            //循环表格行
            for (int i = 1; i < rowsum; i++) {

                QuestionEntity questionEntity = new QuestionEntity();
                //获取当前行
                Row row = sheet.getRow(i);
                //获取第一个单元格的内容 标题
                String title = row.getCell(0).getStringCellValue();
                //设置到题目对象标题属性
                questionEntity.setTitle(title);
                System.out.println("title:" + title);

                //获取第二个单元格的内容 答案
                String answer = row.getCell(1).getStringCellValue();
                questionEntity.setAnswer(answer);
                System.out.println("answer:" + answer);

                //获取第三个单元格内容 题目难度
                double level = row.getCell(2).getNumericCellValue();
                questionEntity.setLevel((int) level);

                //获取第四个单元格的内容 paix
                questionEntity.setDisplayOrder((int) row.getCell(3).getNumericCellValue());

                //获取副标题
                questionEntity.setSubTitle(row.getCell(4).getStringCellValue());
                //题目类型
                questionEntity.setType((long) row.getCell(5).getNumericCellValue());
                //是否显示
                questionEntity.setEnable((int) row.getCell(6).getNumericCellValue());

                //把创建题目对象，添加到题目集合
                list.add(questionEntity);

                //调用数据库保存方法，保存题目数据到书籍
                this.save(questionEntity);
            }

            result.put("result", true);
            result.put("msg", "导入成功");
            result.put("num", list.size());
            System.out.println("导入成功总条数:" + list.size());
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
            result.put("msg", "文件导入失败，" + e.getMessage());
            result.put("num", 0);
        }
        return result;

    }


    @Override
    public Workbook exportExcel() {
        // 创建新的Excel 工作簿
        Workbook workbook = new HSSFWorkbook();

        // 在Excel工作簿中建一工作表，其名为缺省值 Sheet0
        //Sheet sheet = workbook.createSheet();

        // 创建工作表
        Sheet sheet = workbook.createSheet("题库");

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("题目标题");
        row.createCell(1).setCellValue("题目解答");
        row.createCell(2).setCellValue("题目难度等级");
        row.createCell(3).setCellValue("排序");
        row.createCell(4).setCellValue("副标题");
        row.createCell(5).setCellValue("题目类型");
        row.createCell(6).setCellValue("是否显示");
        List<QuestionEntity> list = this.list();
        for (int i = 0; i < list.size(); i++) {
            Row row2 = sheet.createRow(i + 1);
            //创建表格
            row2.createCell(0).setCellValue(list.get(i).getTitle());
            row2.createCell(1).setCellValue(list.get(i).getAnswer());
            row2.createCell(2).setCellValue(list.get(i).getLevel());
            row2.createCell(3).setCellValue(list.get(i).getDisplayOrder());
            row2.createCell(4).setCellValue(list.get(i).getSubTitle());
            row2.createCell(5).setCellValue(list.get(i).getType());
            row2.createCell(6).setCellValue(list.get(i).getEnable());

        }

        return workbook;
    }

    @Override
    public List<Map<String, Object>> countTypeQuestion() {
        QueryWrapper<QuestionEntity> queryWrapper = new QueryWrapper<QuestionEntity>().select("TYPE,COUNT(TYPE) AS num").groupBy("type");
        List<Map<String,Object>> mapList = questionDao.selectMaps(queryWrapper);
        return mapList;
    }

}