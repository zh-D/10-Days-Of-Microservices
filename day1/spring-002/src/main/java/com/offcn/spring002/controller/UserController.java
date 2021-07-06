package com.offcn.spring002.controller;

import com.offcn.spring002.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    // 创建一个集合，存储数据
    // 多个用户修改相同的数据，就会产生冲突。所以要加锁。a修改数据的时候加锁，b不能修改
    List<User> userList = Collections.synchronizedList(new ArrayList<>());

    // 获取全部用户数据
    @GetMapping("/")
    public List<User> findAll() {
        return userList;
    }

    // 新增用户
    // RequestBody 接收前端传过来的 json 格式的数据
    @PostMapping("/")
    public String add(@RequestBody User user) {
        userList.add(user);
        return "add-sucess";
    }

    // 查询指定用户消息
    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") Long id) {
        for (User user: userList) {
            // 判断用户和查询编号是否相同
            if (user.getId().longValue() == id.longValue()) {
                return user;
            }
        }

        return null;


    }

    // 更新指定用户信息
    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, User user) {
        // 遍历集合
        for (User userSrc : userList) {
            if (id.longValue() == userSrc.getId().longValue()) {
                // 修改用户信息
                userSrc.setName(user.getName());
                userSrc.setAge(user.getAge());
                return "update-ok";
            }
        }
        return "update-fail";
    }

    public String delete(Long id) {
        // 查询对象
        User user = this.findOne(id);
        // 移除
        userList.remove(user);
        return "delete-ok";
    }
}
