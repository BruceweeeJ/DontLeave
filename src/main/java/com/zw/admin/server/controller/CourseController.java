package com.zw.admin.server.controller;

import com.zw.admin.server.annotation.LogAnnotation;
import com.zw.admin.server.model.CourseList;
import com.zw.admin.server.service.CourseListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "课程")

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseListService courseListService;
    @LogAnnotation
    @RequestMapping("/save")
    @ApiOperation(value = "创建课程")
    public int saveCourse(@RequestBody CourseList courseList) {
        try {
            courseListService.saveCourse(courseList);
            return 200;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
