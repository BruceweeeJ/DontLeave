package com.zw.admin.server.controller;

import com.zw.admin.server.annotation.LogAnnotation;
import com.zw.admin.server.dao.CourseDao;
import com.zw.admin.server.dao.CourseListDao;
import com.zw.admin.server.model.Course;
import com.zw.admin.server.model.CourseList;
import com.zw.admin.server.model.JsonResult;
import com.zw.admin.server.model.User;
import com.zw.admin.server.service.CourseListService;
import com.zw.admin.server.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "课程")

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseListService courseListService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseListDao courseListDao;
    @Autowired
    private CourseDao courseDao;
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

    @LogAnnotation
    @RequestMapping("/showMember")
    @ApiOperation(value = "显示该课程所有学生")
    public List<Course> showMember(@RequestBody Course course) {

       return courseService.showMember(course.getCourseNumber());
    }

    @LogAnnotation
    @RequestMapping("/courseInfo")
    @ApiOperation(value = "显示该课程信息")
    public CourseList courseInfo(@RequestBody Course course) {
        return courseListDao.courseInfo(course.getCourseNumber());
    }

    @LogAnnotation
    @RequestMapping("/search")
    @ApiOperation(value = "搜索课程")
    public ResponseEntity<JsonResult> search(@RequestBody Course course) {
        JsonResult result =new JsonResult();
        try {
            CourseList courseList = courseListDao.courseInfo(course.getCourseNumber());
            if (courseList == null) {
                result.setCode("400");
                result.setResult("没有这个课程");
                result.setStatus("error");
            }
            else {
                result.setCode("200");
                result.setResult(courseList);
                result.setStatus("success");
            }
        }
        catch (Exception e) {
            result.setCode("400");
            result.setResult(e.getClass().getName() + ":" + e.getMessage());
            result.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @LogAnnotation
    @RequestMapping("/joinCourse")
    @ApiOperation(value = "显示该课程信息")
    public ResponseEntity<JsonResult> joinCourse(@RequestBody Course course) {
        JsonResult result =new JsonResult();
        try {
            Course c = courseDao.isJoined(course.getCourseNumber(),course.getStuId());
            if (c == null) {
                courseDao.joinCourse(course);
                result.setCode("200");
                result.setResult("成功加入课程");
                result.setStatus("success");
            }
            else {
                result.setCode("400");
                result.setResult("已加入该课程");
                result.setStatus("error");
            }
        }
        catch (Exception e) {
            result.setCode("400");
            result.setResult(e.getClass().getName() + ":" + e.getMessage());
            result.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
}
