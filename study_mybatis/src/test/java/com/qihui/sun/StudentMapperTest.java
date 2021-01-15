package com.qihui.sun;

import com.qihui.sun.dao.StudentMapper;
import com.qihui.sun.dao.TeacherMapper;
import com.qihui.sun.model.Student;
import com.qihui.sun.model.Teacher;
import com.qihui.sun.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StudentMapperTest {

    @Test
    public void getAllStudentTest(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.queryAllStudents();
        students.forEach(System.out::println);
        sqlSession.close();
    }
}
