package com.dbmsproject.campusconnect;

import java.io.Serializable;

public class CurrentCourse implements Serializable {
    int cid;
    String coursename;
    String coursedescription;

    public CurrentCourse(int cid, String coursename, String coursedescription) {
        this.cid = cid;
        this.coursename = coursename;
        this.coursedescription = coursedescription;
    }

    public CurrentCourse() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursedescription() {
        return coursedescription;
    }

    public void setCoursedescription(String coursedescription) {
        this.coursedescription = coursedescription;
    }
}
