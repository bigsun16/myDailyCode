package com.qihui.sun;

import com.qihui.sun.dao.TeacherMapper;
import com.qihui.sun.model.Teacher;
import com.qihui.sun.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class TeacherMapperTest {

    @Test
    public void getTeacherByIdTest(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacherById = mapper.getTeacherById(1);
        System.out.println(teacherById);
        sqlSession.close();
    }
}
