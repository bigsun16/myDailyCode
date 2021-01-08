package com.qihui.sun.dao;

import com.qihui.sun.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();
    User getUserById(long id);
    void addUser(User user);
    //    @Param注解：多个基本类型和String时需要加，单个属性可加可不加，引用对象不用加
    //    能用#{}尽量不用${},#{}解析后会加引号，有效防止sql注入，${}不会加引号
    @Select("select * form user where id=#{userId} and name=#{userName}")
    User getUserByIdAndName(@Param("userID") long userId, @Param("userName")String userName);
    void updateUser(User user);
    void deleteUserById(long id);
}
