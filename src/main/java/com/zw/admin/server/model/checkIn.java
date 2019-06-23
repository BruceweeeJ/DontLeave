package com.zw.admin.server.model;

public class checkIn {
    private String courseNumber;
    private String stuId;
    private String checkTime;
    private int tag;

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public checkIn(String courseNumber, String stuId, String checkTime, int tag) {
        this.courseNumber = courseNumber;
        this.stuId = stuId;
        this.checkTime = checkTime;
        this.tag = tag;
    }

    public checkIn() {
    }
}
