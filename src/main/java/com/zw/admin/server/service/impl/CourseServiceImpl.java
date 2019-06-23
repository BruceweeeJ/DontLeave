package com.zw.admin.server.service.impl;

import com.zw.admin.server.dao.CourseDao;
import com.zw.admin.server.model.Course;
import com.zw.admin.server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getCourses(String stuId) {
        return courseDao.getCourses(stuId);
    }

    @Override
    public List<Course> showMember(String courseNumber) {
        return courseDao.showMember(courseNumber);
    }
}
