//package com.offcn.spring002.service.impl;
//
//import com.offcn.spring002.pojo.User;
//import com.offcn.spring002.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class UserServiceimpl implements UserService {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public List<User> findAll() {
//        return jdbcTemplate.query("select * from tb_user");
//    }
//
//    @Override
//    public User findOne(Long id) {
//        return null;
//    }
//
//    @Override
//    public void add(User user) {
//
//    }
//
//    @Override
//    public void update(User user) {
//
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
//}
