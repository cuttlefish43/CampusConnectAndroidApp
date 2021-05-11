package com.dbmsproject.campusconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "campusconnect.db", null, 1);
    }

    String sqlstatment;

    @Override
    public void onCreate(SQLiteDatabase db) {

        sqlstatment = "create table if not exists usertable(id integer primary key autoincrement, username varchar(40),useremail varchar(30),userpassword varchar(20))";
        db.execSQL(sqlstatment);
        //sqlstatment = "create table if not exists faculty(id integer primary key autoincrement, facultyname varchar(40),facultyemail varchar(30),facultypassword varchar(20))";
        //db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists courses(id integer primary key autoincrement, coursename varchar(70), description text(200))";
        db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists studenttocourses(id integer primary key autoincrement, studentid integer not null, courseid integer not null,foreign key(studentid) references usertable(id) on delete cascade,foreign key(courseid) references courses(id) on delete cascade)";
        db.execSQL((sqlstatment));
        sqlstatment = "create table if not exists facultytocourses(id integer primary key autoincrement,facultyid integer not null, courseid integer not null,foreign key(facultyid) references usertable(id) on delete cascade,foreign key(courseid) references courses(id) on delete cascade)";
        db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists announcement(id integer primary key autoincrement, postdate datetime default current_timestamp, courseid integer not null, info text(400),author integer not null, foreign key(courseid) references courses(id) on delete cascade,foreign key(author) references usertable(id) on delete cascade)";
        db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists materials(id integer primary key autoincrement,  courseid integer not null, filelinks varchar(200), foreign key(courseid) references courses(id) on delete cascade)";
        db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists approvals(id integer primary key autoincrement, facultyid integer not null, courseid integer not null, foreign key(facultyid) references usertable(id) on delete cascade,foreign key(courseid) references courses(id) on delete cascade)";
        db.execSQL(sqlstatment);
        sqlstatment = "create table if not exists disscussion(id integer primary key autoincrement, userid integer not null, message text(80), courseid integer not null, foreign key(userid) references usertable(id) on delete cascade,foreign key(courseid) references courses(id) on delete cascade)";
        db.execSQL(sqlstatment);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /* user login  methods*/
    public int checkUser(String email, String password){
        SQLiteDatabase db;
        int status;
        db=this.getReadableDatabase();
        String sqlstatment1="select * from usertable where useremail = \""+email+"\"";
        Cursor cursor= db.rawQuery(sqlstatment1,null);
        if(cursor.moveToFirst()){
            status= cursor.getInt(0);
            String dbemail= cursor.getString(2);
            String dbpassword= cursor.getString(3);
            cursor.close();
            db.close();
            if(dbemail.equals(email) && dbpassword.equals(password)){
                return status;//unique id
            }else{
                return -1;
            }
        }else{
            cursor.close();
            db.close();
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
                cu.close();
                db.close();
                return 2; // email exists
            } else {
                cu.close();
                db.close();
                db = this.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("username", sname);
                cv.put("useremail", email);
                cv.put("userpassword", password);
                long status = db.insert("usertable", null, cv);
                if (status > 0) {
                    cu.close();
                    db.close();
                    return 1; // user created
                } else {

                    cu.close();
                    db.close();
                    return -1; // user creation error
                }
            }
        }
        cu.close();
        db.close();
        return -1;
    }
    public OCourses getCourse(int courseid){
        SQLiteDatabase db;
        OCourses obj = new OCourses();
        db= this.getReadableDatabase();
        String sqlstatment2 ="select * from courses where id = \"" +courseid+"\""; //for testing changed table
        Cursor cursor;
        cursor=db.rawQuery(sqlstatment2,null);

        if(cursor.moveToFirst()){

                int id = cursor.getInt(0);
                String coursename = cursor.getString(1);
                String description = cursor.getString(2);
                //System.out.println("Success"+coursename+" "+description+" ");
                obj= new OCourses(id,coursename,description);
        }
        //System.out.println("Success ");
        cursor.close();
        db.close();
        return obj;

    }
    /*Course adder methods*/
    public List<OCourses> getAllCourses(){
        List<OCourses> retList= new ArrayList<>();
        SQLiteDatabase db;
        db= this.getReadableDatabase();
        String sqlstatment2 ="select * from courses"; //for testing changed table
        Cursor cursor;
        cursor=db.rawQuery(sqlstatment2,null);
        if(cursor.moveToFirst()){

            do {

                int id = cursor.getInt(0);
                String coursename = cursor.getString(1);
                String description = cursor.getString(2);
                //System.out.println("Success"+coursename+" "+description+" ");
                OCourses obj= new OCourses(id,coursename,description);
                retList.add(obj);

            }while(cursor.moveToNext());
        }
        //System.out.println("Success ");
        cursor.close();
        db.close();
        return retList;
    }
    public List<OCourses> getRegCourses(int sid){
        List<OCourses> retList= new ArrayList<>();
        SQLiteDatabase db;
        db= this.getReadableDatabase();
        //student table check
        String sqlstatment2 ="select * from courses where id in (select courseid from studenttocourses where studentid = \""+sid+"\")";
        Cursor cursor;
        cursor=db.rawQuery(sqlstatment2,null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String coursename = cursor.getString(1);
                String description = cursor.getString(2);
                OCourses obj= new OCourses(id,coursename,description);
                retList.add(obj);
            }while(cursor.moveToNext());
        }
        cursor.close();
        //faculty table check
        sqlstatment2="select * from courses where id in (select courseid from facultytocourses where facultyid = \""+sid+"\")";
        cursor=db.rawQuery(sqlstatment2,null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String coursename = cursor.getString(1);
                String description = cursor.getString(2);
                OCourses obj= new OCourses(id,coursename,description);
                retList.add(obj);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return retList;

    }
    //methods for admin
    public List<Ousers> allUsers(){
        List<Ousers> retList= new ArrayList<>();
        SQLiteDatabase db;
        db= this.getReadableDatabase();
        String sqlstatment3 = "select * from usertable";
        Cursor cursor;
        cursor =db.rawQuery(sqlstatment3,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name= cursor.getString(1);
                String email= cursor.getString(2);

                Ousers ouser= new Ousers(id,name,email);
                retList.add(ouser);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return retList;

    }
    public int addCourse(OCourses obj) {
        SQLiteDatabase db;
        // first check if the course name already exists
        db = this.getReadableDatabase();
        String sqlstatment4 = "select count(*) from courses where coursename = \"" + obj.getCoursename() + "\"";
        Cursor cu;
        int count;
        cu = db.rawQuery(sqlstatment4, null);
        if (cu.moveToFirst()) {
            count = cu.getInt(0);
            db.close();

            if (count >= 1) {
                cu.close();
                db.close();
                return 0; // course name taken
            }
        }
        cu.close();
        db.close();
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("coursename", obj.getCoursename());
        cv.put("description", obj.getDescription());

        long status = db.insert("courses", null, cv);
        //sqlstatment4 = "insert into courses( coursename, description) values (\"" + obj.getCoursename() + "\", \"" + obj.getDescription() + "\")";
        //cu = db.rawQuery(sqlstatment4, null);
        if (status >0) {

            db.close();
            return 1; //insert successfull
        } else {

            db.close();
            return -1; //insert error
        }

    }


    public int checkStudenttoCourse(int uid, int cid) {
        SQLiteDatabase db;
        db= this.getReadableDatabase();
        String sqlstatment5="select count(*) from studenttocourses where studentid = \"" + uid + "\" and courseid = \"" + cid + "\"";
        Cursor cu;
        int count=0;
        cu= db.rawQuery(sqlstatment5,null);
        if(cu.moveToFirst()){
            count=cu.getInt(0);
            System.out.println("This is studenttocourse count"+count);
            cu.close();
            db.close();

            if(count >=1){
                return 1; //user is registered as student
            }else{
                return -1;
            }
        }else{
            cu.close();
            db.close();
            return 0;
        }
    }

    public int checkFacultytoCourse(int userid, int courseid) {
        SQLiteDatabase db;
        db= this.getReadableDatabase();
        String sqlstatment5="select count(*) from facultytocourses where facultyid = \"" + userid + "\" and courseid = \"" + courseid + "\"";
        Cursor cu;
        int count=0;
        cu= db.rawQuery(sqlstatment5,null);
        if(cu.moveToFirst()){
            count=cu.getInt(0);
            System.out.println("This is studenttocourse count"+count);
            cu.close();
            db.close();

            if(count >=1){
                return 1; //user is registered as faculty
            }else{
                return -1;
            }
        }else{
            cu.close();
            db.close();
            return 0;
        }
    }

    public int registerFaculty(int userid, int courseid) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("facultyid", userid);
        cv.put("courseid", courseid);
        long status=db.insert("approvals",null,cv);
        db.close();
        if(status > 0){
            return 1;
        }else{
            return -1;
        }

    }

    public int registerStudent(int userid, int courseid) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("studentid", userid);
        cv.put("courseid", courseid);
        long status=db.insert("studenttocourses",null,cv);
        db.close();
        if(status > 0){
            return 1;
        }else{
            return -1;
        }
    }

    public List<Approval> getAllApprovals() {
        List<Approval> pendingList=new ArrayList<>();
        SQLiteDatabase db;
        db =this. getReadableDatabase();
        Cursor cursor;
        String sqlstatment6="select usertable.id,usertable.username,courses.id,courses.coursename from approvals join usertable  on approvals.facultyid = usertable.id join courses on approvals.courseid = courses.id";
        cursor=db.rawQuery(sqlstatment6,null);
        if(cursor.moveToFirst()){
            do{
                int userid = cursor.getInt(0);
                String username= cursor.getString(1);
                int courseid= cursor.getInt(2);
                String coursename= cursor.getString(3);

                Approval ouser= new Approval(userid,courseid,username,coursename);
                pendingList.add(ouser);

            }while(cursor.moveToNext());

        }
        return pendingList;
    }

    public List<Oothers> getAllAnnouncements(int userid, int courseid) {
        List<Oothers> annLists= new ArrayList<>();
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cursor;
        String sqlstatment7="select * from announcement where courseid = \""+courseid+"\" order by id desc";
        cursor = db.rawQuery(sqlstatment7,null);
        if(cursor.moveToFirst()){
            do{
                int titleid=cursor.getInt(0);
                String titledate= cursor.getString(1);
                String descp= cursor.getString(3);
                String title= "No:"+Integer.toString(titleid) + " At: " + titledate;
                Oothers anouncement= new Oothers(title,descp);
                annLists.add(anouncement);
            }while(cursor.moveToNext());
        }
        return annLists;
    }

    public List<Oothers> getAllMaterials(int userid, int courseid) {
        List<Oothers> annLists= new ArrayList<>();
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cursor;
        String sqlstatment7="select * from materials where courseid = \""+courseid+"\" order by id desc";
        cursor = db.rawQuery(sqlstatment7,null);
        if(cursor.moveToFirst()){
            do{

                String title= "Materail id: "+cursor.getString(0);
                String descp= cursor.getString(3);

                Oothers material= new Oothers(title,descp);
                annLists.add(material);
            }while(cursor.moveToNext());
        }
        return annLists;

    }

    public List<Oothers> getAlldisscussions(int userid, int courseid) {
        List<Oothers> annLists= new ArrayList<>();
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cursor;
        String sqlstatment7="select * from disscussion where courseid = \""+courseid+"\" order by id desc";
        cursor = db.rawQuery(sqlstatment7,null);
        if(cursor.moveToFirst()){
            do{
                String title= "User id: "+cursor.getString(1);
                String descp= cursor.getString(2);
                Oothers discuss= new Oothers(title,descp);
                annLists.add(discuss);
            }while(cursor.moveToNext());
        }
        return annLists;
    }



    public int sendMessage(int userid, int courseid, String message) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userid", userid);
        cv.put("message",message);
        cv.put("courseid",courseid);
        long status= db.insert("disscussion",null,cv);
        db.close();
        if(status > 0){
            return 1;
        }else{
            return -1;
        }
    }

    public int addMaterial(int courseid, String uploads) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("courseid",courseid);
        cv.put("filelinks",uploads);
        long status= db.insert("materials",null,cv);
        db.close();
        if(status > 0){
            return 1;
        }else{
            return -1;
        }
    }

    public List<Ousers> allUsersforAdmin() {
        List<Ousers> retList= new ArrayList<>();
        SQLiteDatabase db;
        db= this.getReadableDatabase();
        String sqlstatment3 = "select * from usertable";
        Cursor cursor;
        cursor =db.rawQuery(sqlstatment3,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name= cursor.getString(1);
                String email= cursor.getString(2);
                //String combined= "Name: "+name+" \nEmail"+email;
                Ousers obj= new Ousers(id,name,email);
                retList.add(obj);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return retList;
        
    }

    public List<OCourses> allCoursesforAdmin() {
        List<OCourses> retList= new ArrayList<>();
        SQLiteDatabase db;
        db= this.getReadableDatabase();
        String sqlstatment3 = "select * from courses";
        Cursor cursor;
        cursor =db.rawQuery(sqlstatment3,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name= cursor.getString(1);
                String descript = cursor.getString(2);
                OCourses obj= new OCourses(id,name,descript);
                retList.add(obj);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return retList;
    }

    public int deleteUser(int id) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        long status= db.delete("usertable","id= \""+id+"\"", null);
        db.close();
        if(status > 0){
            return 1;
        }else{
            return -1;
        }
        
    }

    public int rejectFaculty(int userid, int courseid) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        
        long status= db.delete("approvals","facultyid= \""+userid+"\" and courseid= \""+courseid+"\"", null);
        db.close();
        if(status > 0){
            return 1;
        }else{
            return -1;
        }
    }

    public int acceptFaculty(int userid, int courseid) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("courseid",courseid);
        cv.put("facultyid",userid);
        long status= db.insert("facultytocourses",null,cv);
        db.close();
        if(status > 0){
            db = this.getWritableDatabase();

            status= db.delete("approvals","facultyid= \""+userid+"\" and courseid= \""+courseid+"\"", null);
            db.close();
            
            return 1;
        }else{
            return -1;
        }
        
    }

    public int deleteCourse(int courseid) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        long status= db.delete("courses","id= \""+courseid+"\"", null);
        db.close();
        if(status > 0){
            return 1;
        }else{
            return -1;
        }
    }

    public int addAnnouncement(int courseid, String announce) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("courseid", courseid);
        cv.put("info",announce);
        cv.put("author",0);
        long status= db.insert("announcement",null,cv);
        db.close();
        if(status > 0){
            return 1;
        }else{
            return -1;
        }
    }
}
