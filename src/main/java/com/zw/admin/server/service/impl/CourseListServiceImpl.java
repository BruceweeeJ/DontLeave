package com.zw.admin.server.service.impl;

import com.zw.admin.server.dao.CourseListDao;
import com.zw.admin.server.model.CourseList;
import com.zw.admin.server.service.CourseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseListServiceImpl implements CourseListService {
    @Autowired
    private CourseListDao courseListDao;
    @Override
    public void saveCourse(CourseList course) {
        courseListDao.save(course);

    }
}
