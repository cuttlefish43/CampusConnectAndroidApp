package com.dbmsproject.campusconnect;

public class Approval {
    int courseid;
    int userid;
    String username;
    String coursename;

    public Approval(int userid, int courseid, String username, String coursename) {
        this.courseid = courseid;
        this.userid = userid;
        this.username = username;
        this.coursename = coursename;
    }

    public Approval() {
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
}

