package com.zw.admin.server.model;

public class CourseList {
    private String courseNumber;
    private String courseName;
    private String teachName;
    private String teachNumber;
    private String className;
    private String schoolTerm;
    private String schoolName;
    private String requirement;
    private String progress;
    private String schedule;
    private int tag;

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public String getTeachNumber() {
        return teachNumber;
    }

    public void setTeachNumber(String teachNumber) {
        this.teachNumber = teachNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSchoolTerm() {
        return schoolTerm;
    }

    public void setSchoolTerm(String schoolTerm) {
        this.schoolTerm = schoolTerm;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public CourseList() {
    }

    public CourseList(String courseNumber, String courseName, String teachName, String teachNumber, String className, String schoolTerm, String schoolName, String requirement, String progress, String schedule, int tag) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.teachName = teachName;
        this.teachNumber = teachNumber;
        this.className = className;
        this.schoolTerm = schoolTerm;
        this.schoolName = schoolName;
        this.requirement = requirement;
        this.progress = progress;
        this.schedule = schedule;
        this.tag = tag;
    }
}
