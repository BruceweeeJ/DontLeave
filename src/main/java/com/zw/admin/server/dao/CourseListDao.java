package com.zw.admin.server.dao;

import com.zw.admin.server.model.CourseList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseListDao {
    @Insert("insert into courselist(courseNumber, courseName, teachName, className, schoolName, schoolTerm, requirement, progress, schedule) " +
            "values(#{courseNumber}, #{courseName}, #{teachName}, #{className}, #{schoolName}, #{schoolTerm}, #{requirement}, #{progress}, #{schedule})")
    void save(CourseList course);
}
