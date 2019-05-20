package com.zw.admin.server.dao;

import com.zw.admin.server.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseDao {
    @Select("select * from course c where c.stuId = #{stuId}")
    List<Course> getCourses (String stuId);
}
