package com.dbmsproject.campusconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "campusconnect.db", null, 1);
    }

    String sqlstatment;

    @Override
    public void onCreate(SQLiteDatabase db) {

        sqlstatment = "create table if not exists usertable(id integer primary key autoincrement, username varchar(40),useremail varchar(30),userpassword varchar(20),isstudent integer,isfaculty integer)";
        db.execSQL(sqlstatment);
        //sqlstatment = "create table if not exists faculty(id integer primary key autoincrement, facultyname varchar(40),facultyemail varchar(30),facultypassword varchar(20))";
        //db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists courses(id integer primary key autoincrement, coursename varchar(70), description text(200))";
        db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists studenttocourses(id integer primary key autoincrement, studentid integer not null, courseid integer not null,foreign key(studentid) references usertable(id) on delete cascade,foreign key(courseid) references courses(id) on delete cascade)";
        db.execSQL((sqlstatment));
        sqlstatment = "create table if not exists facultytocourses(id integer primary key autoincrement,facultyid integer not null, courseid integer not null,foreign key(facultyid) references usertable(id) on delete cascade,foreign key(courseid) references courses(id) on delete cascade)";
        db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists announcement(id integer primary key autoincrement, postdate timestamp, courseid integer not null, info text(400),author integer not null, foreign key(courseid) references courses(id) on delete cascade,foreign key(author) references usertable(id) on delete cascade)";
        db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists materials(id integer primary key autoincrement, postdate timestamp, courseid integer not null, filelinks varchar(200), foreign key(courseid) references courses(id) on delete cascade)";
        db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists approvals(id integer primary key autoincrement, facultyid integer not null, courseid integer not null, foreign key(facultyid) references usertable(id) on delete cascade,foreign key(courseid) references courses(id) on delete cascade)";
        db.execSQL(sqlstatment);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public int checkUser(String email, String password){
        SQLiteDatabase db;
        int status;
        db=this.getReadableDatabase();
        String sqlstatment1="select * from usertable where useremail = \""+email+"\"";
        Cursor cursor= db.rawQuery(sqlstatment1,null);
        if(cursor.moveToFirst()){
            String dbemail= cursor.getString(2);
            String dbpassword= cursor.getString(3);
            if(dbemail.equals(email) && dbpassword.equals(password)){
                return 1;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }
    public int addUser(String sname, String email, String password) {
        SQLiteDatabase db;
        int count;
        //if part
        db = this.getReadableDatabase();
        String sqlstatment1 = "select count(*) from usertable where useremail = \"" + email + "\"";
        Cursor cu;
        cu = db.rawQuery(sqlstatment1, null);
        if (cu.moveToFirst()) {
            count = cu.getInt(0);
            db.close();
            if (count >= 1) {

                return 2; // email exists
            } else {
                db = this.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("username", sname);
                cv.put("useremail", email);
                cv.put("userpassword", password);
                long status = db.insert("usertable", null, cv);
                if (status > 0) {
                    return 1; // user created
                } else {
                    return -1; // user creation error
                }
            }
        }
        db.close();
        return -1;
    }
}