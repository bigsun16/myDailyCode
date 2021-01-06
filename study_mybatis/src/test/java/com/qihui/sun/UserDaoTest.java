package com.qihui.sun;

import com.qihui.sun.dao.UserDao;
import com.qihui.sun.model.User;
import com.qihui.sun.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDaoTest {

    @Test
    public void getUserList(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
//        UserDao mapper = sqlSession.getMapper(UserDao.class);
//        List<User> userList = mapper.getUserList();
        List<User> userList = sqlSession.selectList("com.qihui.sun.dao.UserDao.getUserList");
        userList.forEach(System.out::println);
        sqlSession.close();
    }
}
