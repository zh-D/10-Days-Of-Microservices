package com.offcn.spring002.dao;

import com.offcn.spring002.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

// 使用接口方式生成日志文件
@Mapper
@Repository
public interface UserDao {
    // 获取全部用户数据
    @Select("select * from tb_user")
    public List<User> findAll();

    // 获取指定编号的用户数据
    @Select("select * from tb_user where id=#{id}")
    public User findOne(Long id);

    // 新增用户数据
    @Insert("insert into tb_user(name,age) values(#{name}, #{age})")
    public void add(User user);

    // 修改数据
    @Update("update tb_use set name=#{name}, age=#{age} where id=#{id}")
    public void update(User user);

    // 删除数据
    @Delete("delete from db_user where id=#{id}")
    public void delete(Long id);
}
