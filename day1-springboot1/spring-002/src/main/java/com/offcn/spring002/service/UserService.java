package com.offcn.spring002.service;

import com.offcn.spring002.pojo.User;

import java.util.List;

public interface UserService {

    // 获取全部用户数据
    public List<User> findAll();

    public  User findOne(Long id);

    public void add(User user);

    public void update(User user);

    public void delete(Long id);
}
