package com.dbmsproject.campusconnect;

public class OCourses {
    private int courseid;
    private String coursename;
    private String description;

    public OCourses(int courseid, String coursename, String description) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.description = description;
    }

    public OCourses() {
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OCourses{" +
                "courseid=" + courseid +
                ", coursename='" + coursename + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
