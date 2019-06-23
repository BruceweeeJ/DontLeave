package com.zw.admin.server.dao;

import com.zw.admin.server.model.Course;
import com.zw.admin.server.model.CourseList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CourseListDao {
    @Insert("insert into courselist(courseNumber, courseName, teachName, className, schoolName, schoolTerm, requirement, progress, schedule,teachNumber) " +
            "values(#{courseNumber}, #{courseName}, #{teachName}, #{className}, #{schoolName}, #{schoolTerm}, #{requirement}, #{progress}, #{schedule},#{teachNumber})")
    void save(CourseList course);

    @Select("select * from courselist c where c.courseNumber=#{courseNumber}")
    CourseList courseInfo(String courseNumber);

}
