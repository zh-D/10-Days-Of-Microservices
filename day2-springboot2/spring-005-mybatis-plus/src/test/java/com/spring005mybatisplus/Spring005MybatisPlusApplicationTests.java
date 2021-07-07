package com.spring005mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring005mybatisplus.dao.mapper.UserMapper;
import com.spring005mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class Spring005MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));

        // null 表示无条件选择数据
        List<User> userList = userMapper.selectList(null);

        for (User user:userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("Helen");
        user.setAge(18);
        user.setEmail("test@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }

    @Test
    public void testIdWorker(){
        long id = IdWorker.getId();
        System.out.println("id:"+id);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1L);
        user.setAge(28);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    @Test
    public void testInsertDate(){
        User user = new User();
        user.setName("Helen004");
        user.setAge(18);
        user.setEmail("test@qq.com");
        //执行保存方法
        int result = userMapper.insert(user);
        System.out.println(result);//保存影响的行数
        System.out.println(user);//id自动填写
    }

    @Test
    public void testUpdateByIdDate(){
        User user = new User();
        user.setId(1412310407070089219L);
        user.setName("修改-test001");
        user.setAge(100);

        int result = userMapper.updateById(user);
        System.out.println(result);

    }

    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);
        List<User> userList = page.getRecords();

        for(User user:userList) {
            System.out.println(user);
        }

        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getPages());//总页数
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.hasPrevious());//是否有上一页

    }

    @Test
    public void testSelectMapsPage() {
        Page<User> page = new Page<>(1, 5);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);

        List<Map<String, Object>> mapList = mapIPage.getRecords();

        for (Map<String, Object> map : mapList) {
            System.out.println(map.get("id"));
            System.out.println(map.get("name"));
            System.out.println(map.get("age"));
            System.out.println(map.get("email"));
        }

        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());

    }

    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(8L);
        System.out.println(result);
    }

    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(8, 9, 10));
        System.out.println(result);
    }

    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);

        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    @Test
    public void testLogicDelete() {
        int result = userMapper.deleteById(1L);
        System.out.println(result);
    }

    @Test
    public void testLogicDeleteSelect() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Tom");

        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

 }
