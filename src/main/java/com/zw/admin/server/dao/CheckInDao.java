package com.zw.admin.server.dao;

import com.zw.admin.server.model.checkIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CheckInDao {
    @Update("update checkin t set t.checkTime = #{checkTime} , t.tag=1 where t.courseNumber = #{courseNumber} and t. stuId=#{stuId} and t.checkTime=#{lastCheckTime}")
    int checkIn(String checkTime,String courseNumber,String stuId,String lastCheckTime);

    @Insert("insert into checkin(courseNumber,stuId,checkTime,tag) values(#{courseNumber},#{stuId},#{checkTime},#{tag})")
    void initCheck(String courseNumber,String stuId,String checkTime,int tag);

    @Select("select * from checkin c  where c.courseNumber = #{courseNumber} and c.stuId=#{stuId}")
    List<checkIn> getCheckList(String courseNumber, String stuId);
}
//@Update("update courselist t set t.tag = 0 where t.courseNumber = #{courseNumber}")