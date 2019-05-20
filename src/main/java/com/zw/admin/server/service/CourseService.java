package com.zw.admin.server.service;

import com.zw.admin.server.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourses(String stuId);
}
