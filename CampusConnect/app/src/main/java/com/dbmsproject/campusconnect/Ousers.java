package com.dbmsproject.campusconnect;

public class Ousers {
    private int id;
    private String username;
    private String useremail;


    public Ousers(int id, String username, String useremail ) {
        this.id = id;
        this.username = username;
        this.useremail = useremail;

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



    @Override
    public String toString() {
        return "Ousers{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", useremail='" + useremail + '\'' +

                '}';
    }
}
