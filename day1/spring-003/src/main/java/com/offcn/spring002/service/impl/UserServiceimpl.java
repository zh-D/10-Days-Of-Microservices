package com.offcn.spring002.service.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.offcn.spring002.pojo.User;
import com.offcn.spring002.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from tb_user", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findOne(Long id) {
        return jdbcTemplate.queryForObject("select * from tb_user where id=?", new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update("insert into tb_user(name, age) values(?, ?)", user.getName(), user.getId());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("update tb_user set name=?, age=? where id=?", user.getName(), user.getAge(), user.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from tb_user where id=?", id);
    }
}
