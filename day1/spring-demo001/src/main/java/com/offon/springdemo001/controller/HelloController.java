package com.offon.springdemo001.controller;

import com.offon.springdemo001.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
//@RequestMapping("dd")
// 控制层注解，响应视图的客户请求，返回一个 json 格式。它是一种前端视图层和控制层进行数据交换的格式
public class HelloController {
//    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @GetMapping("hello")
    public String toString() {
        return "Hello SpringBoot!";
    }

    @RequestMapping("hello2")
    public String hello2() {
        return "hello2";
    }

    public User findOne() {
        User user = new User(1,"张三",190.2,new Date());
        return user;
    }
    @RequestMapping("findAll")
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        // ctrl+d
        User u1 = new User(1,"zzs",1234.123,new Date());
        User u2 = new User(2,"2zs",1234.123,new Date());
        User u3 = new User(3,"3zs",1234.123,new Date());
        User u4 = new User(4,"4zs",1234.123,new Date());
        User u5 = new User(5,"5zs",1234.123,new Date());

        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u5);

        return list;
    }

    // 定义字符串接收自定义属性
    @Value("${ip}")
    public String ip;

    @Value("${port}")
    public String port;

    @Value("${user.name}")
    public String name;

    // 显示自定义属性
    @RequestMapping("ip")
    public String demo3() {
        return this.ip;
    }
}
