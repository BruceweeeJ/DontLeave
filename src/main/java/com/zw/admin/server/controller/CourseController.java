package com.zw.admin.server.controller;

import com.zw.admin.server.annotation.LogAnnotation;
import com.zw.admin.server.dao.CheckInDao;
import com.zw.admin.server.dao.CourseDao;
import com.zw.admin.server.dao.CourseListDao;
import com.zw.admin.server.model.*;
import com.zw.admin.server.service.CourseListService;
import com.zw.admin.server.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
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
    @Autowired
    private CheckInDao checkInDao;

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
    @ApiOperation(value = "加入课程")
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

    @LogAnnotation
    @PostMapping("/teachCourse")
    @ApiOperation(value = "显示教师课程")
    public List<CourseList> teachCourse(@RequestBody CourseList course) {
        return courseListDao.teachCourse(course.getTeachNumber());
    }

    @LogAnnotation
    @PostMapping("/raiseCheck")
    @ApiOperation(value = "发起签到")
    public ResponseEntity<JsonResult> raiseCheck(@RequestBody CourseList course) {
        JsonResult result =new JsonResult();
        //course.getSchedule()为获取签到的时间
        try {
            courseListDao.lastCheckTime(course.getLastCheckTime(),course.getCourseNumber());
            //签到表中先初始化所有人的记录，全部先标识为缺勤，之后学生签到之后改成出勤
            List<Course> courses = courseDao.showMember(course.getCourseNumber());
            for (int i = 0; i < courses.size(); i++) {
                checkInDao.initCheck(courses.get(i).getCourseNumber(),courses.get(i).getStuId(),course.getLastCheckTime(),0);
            }
            courseListDao.raiseCheck(course.getCourseNumber());
            result.setCode("200");
            result.setResult("成功发起签到");
            result.setStatus("success");
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
    @PostMapping("/endCheck")
    @ApiOperation(value = "结束签到")
    public ResponseEntity<JsonResult> endCheck(@RequestBody CourseList course) {
        JsonResult result =new JsonResult();
        try {
            courseListDao.endCheck(course.getCourseNumber());
            result.setCode("200");
            result.setResult("签到结束");
            result.setStatus("success");
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
    @PostMapping("/checkIn")
    @ApiOperation(value = "课程签到")
    public ResponseEntity<JsonResult> checkIn(@RequestBody checkIn checkIn) {
        JsonResult result =new JsonResult();
        try {
            if (courseListDao.isChecking(checkIn.getCourseNumber())==1) {
                String lastCheckTime = courseListDao.getLastCheckTime(checkIn.getCourseNumber());
                int flag = checkInDao.checkIn(checkIn.getCheckTime(),checkIn.getCourseNumber(),checkIn.getStuId(),lastCheckTime);
                if (flag==0) {
                    result.setCode("300");
                    result.setResult("无需重复签到");
                    result.setStatus("success");
                    return ResponseEntity.ok(result);
                }
                result.setCode("200");
                result.setResult("签到成功");
                result.setStatus("success");
            } else {
                result.setCode("500");
                result.setResult("老师尚未发起签到");
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
    @PostMapping("/getCheckList")
    @ApiOperation(value = "课程签到")
    public ResponseEntity<JsonResult> getCheckList(@RequestBody checkIn checkIn) {
        JsonResult result =new JsonResult();
        try {
            List<checkIn> c = checkInDao.getCheckList(checkIn.getCourseNumber(),checkIn.getStuId());
            result.setCode("200");
            result.setResult(c);
            result.setStatus("success");
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
    @RequestMapping("/getAll")
    @ApiOperation(value = "获取所有课程")
    public List<CourseList> getAll(){
        return courseListDao.getAll();
    }

}
