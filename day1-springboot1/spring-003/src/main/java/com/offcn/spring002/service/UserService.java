package com.offcn.spring002.service;

import com.offcn.spring002.pojo.User;

import java.util.List;

public interface UserService {

    // 获取全部用户数据
    public List<User> findAll();

    // 获取指定编号的用户数据
    public User findOne(Long id);

    // 新增用户数据
    public void add(User user);

    // 修改数据
    public void update(User user);

    // 删除数据
    public void delete(Long id);
}
