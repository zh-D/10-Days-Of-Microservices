package com.offcn.spring002.controller;

import com.offcn.spring002.pojo.User;
import com.offcn.spring002.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    // 创建一个集合，存储数据
    // 多个用户修改相同的数据，就会产生冲突。所以要加锁。a修改数据的时候加锁，b不能修改
//    List<User> userList = Collections.synchronizedList(new ArrayList<>());

    // 注入服务层
    @Autowired
    private UserService userService;

    // 获取全部用户数据
    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    // 新增用户
    // RequestBody 接收前端传过来的 json 格式的数据
    @PostMapping("/")
    public String add(@RequestBody User user) {
        userService.add(user);
        return "add-sucess";
    }

    // 查询指定用户消息
    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }

    // 更新指定用户信息
    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, User user) {
        // 关联设置 id 到 user 对象
        user.setId(id);
        userService.update(user);

        return "update-success";
    }

    public String delete(Long id) {
        // 移除
        userService.delete(id);
        return "delete-ok";
    }
}
