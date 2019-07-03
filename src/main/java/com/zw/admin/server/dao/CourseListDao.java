package com.zw.admin.server.dao;

import com.zw.admin.server.model.Course;
import com.zw.admin.server.model.CourseList;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseListDao {
    @Select("select * from courselist")
    List<CourseList> getAll();

    @Insert("insert into courselist(courseNumber, courseName, teachName, className, schoolName, schoolTerm, requirement, progress, schedule,teachNumber) " +
            "values(#{courseNumber}, #{courseName}, #{teachName}, #{className}, #{schoolName}, #{schoolTerm}, #{requirement}, #{progress}, #{schedule},#{teachNumber})")
    void save(CourseList course);

    @Select("select * from courselist c where c.courseNumber=#{courseNumber}")
    CourseList courseInfo(String courseNumber);

    @Select("select * from courselist c where c.teachNumber=#{teachNumber}")
    List<CourseList> teachCourse(String teachNumber);

    @Update("update courselist t set t.tag = 1 where t.courseNumber = #{courseNumber}")
    int raiseCheck(String courseNumber);

    @Update("update courselist t set t.tag = 0 where t.courseNumber = #{courseNumber}")
    int endCheck(String courseNumber);

    @Update("update courselist t set t.lastCheckTime = #{lastCheckTime} where t.courseNumber = #{courseNumber}")
    int lastCheckTime(String lastCheckTime,String courseNumber);

    @Select("select lastCheckTime from courselist where courseNumber = #{courseNumber}")
    String getLastCheckTime(String courseNumber);

    @Select("select tag from courselist where courseNumber = #{courseNumber}")
    int isChecking(String courseNumber);

    @Delete("delete from courselist where courseNumber = #{courseNumber}")
    int deleteCourselist(String courseNumber);

    int update(CourseList courseList);
}
