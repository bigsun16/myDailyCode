package com.qihui.sun;

import com.qihui.sun.dao.UserMapper;
import com.qihui.sun.model.User;
import com.qihui.sun.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDaoTest {
    Logger logger = Logger.getLogger(UserDaoTest.class);

    @Test
    public void getUserList() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserList();
//        List<User> userList = sqlSession.selectList("com.qihui.sun.dao.UserMapper.getUserList");
        userList.forEach(System.out::println);
        sqlSession.close();
        logger.info("info log");
        logger.debug("debug log");
        logger.warn("warn log");
        logger.error("error log");
    }

    @Test
    public void getUserById() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void addUser() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(4l);
        user.setGender("女");
        user.setAge(18);
        user.setName("赵六");
        mapper.addUser(user);
//        sqlSession.commit();
        sqlSession.close();
        getUserList();
    }

    @Test
    public void updateUser() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1l);
        user.setGender("女");
        user.setAge(19);
        mapper.updateUser(user);
//        sqlSession.commit();
        sqlSession.close();
        getUserList();
    }

    @Test
    public void deleteUserById() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUserById(4l);
//        sqlSession.commit();
        sqlSession.close();
        getUserList();
    }
}
