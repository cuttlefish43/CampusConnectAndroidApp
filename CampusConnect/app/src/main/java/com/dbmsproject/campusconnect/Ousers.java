package com.dbmsproject.campusconnect;

public class Ousers {
    private int id;
    private String username;
    private String useremail;
    private int isstudent;
    private int isfaculty;

    public Ousers(int id, String username, String useremail, int isstudent, int isfaculty) {
        this.id = id;
        this.username = username;
        this.useremail = useremail;
        this.isstudent = isstudent;
        this.isfaculty = isfaculty;
    }

    public Ousers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getIsstudent() {
        return isstudent;
    }

    public void setIsstudent(int isstudent) {
        this.isstudent = isstudent;
    }

    public int getIsfaculty() {
        return isfaculty;
    }

    public void setIsfaculty(int isfaculty) {
        this.isfaculty = isfaculty;
    }

    @Override
    public String toString() {
        return "Ousers{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", useremail='" + useremail + '\'' +
                ", isstudent=" + isstudent +
                ", isfaculty=" + isfaculty +
                '}';
    }
}
