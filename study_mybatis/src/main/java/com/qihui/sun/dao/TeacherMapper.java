package com.qihui.sun.dao;

import com.qihui.sun.model.Teacher;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {
    @Select("select * from teacher where id=#{id}")
    Teacher getTeacherById(int id);
}
