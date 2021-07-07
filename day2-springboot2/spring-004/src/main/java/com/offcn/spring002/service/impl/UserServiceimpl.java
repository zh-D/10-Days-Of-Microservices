package com.offcn.spring002.service.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.offcn.spring002.dao.UserDao;
import com.offcn.spring002.pojo.User;
import com.offcn.spring002.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    // 注入数据访问层的接口
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
