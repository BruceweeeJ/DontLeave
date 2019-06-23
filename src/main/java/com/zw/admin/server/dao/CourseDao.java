package com.zw.admin.server.dao;

import com.zw.admin.server.model.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseDao {
    @Select("select * from course c where c.stuId = #{stuId}")
    List<Course> getCourses (String stuId);

    @Select("select * from course c where c.courseNumber = #{courseNumber}")
    List<Course> showMember (String courseNumber);

    @Select("select * from course c where c.courseNumber = #{courseNumber} and c.stuId = #{stuId}")
    Course isJoined (String courseNumber, String stuId);

    @Insert("Insert into course(courseNumber,courseName,teachName,stuName,stuId,className)" +
            "values(#{courseNumber},#{courseName},#{teachName},#{stuName},#{stuId},#{className})")
    void joinCourse(Course course);
}
