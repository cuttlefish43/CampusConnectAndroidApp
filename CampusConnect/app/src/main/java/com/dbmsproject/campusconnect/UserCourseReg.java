package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class UserCourseReg extends Activity {
    Switch sw_faculty;
    Button btn_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_course_reg);
        sw_faculty=findViewById(R.id.sw_faculty);

        btn_confirm=findViewById(R.id.btn_confirm);
        Intent data= getIntent();
        int userid=data.getIntExtra("reguserid",-1);
        int courseid =data.getIntExtra("regcourseid",-1);
        System.out.println(userid);
        System.out.println(courseid);


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int regstatus;
                DatabaseHelper databaseHelper = new DatabaseHelper(UserCourseReg.this);
                if(sw_faculty.isChecked()){

                    if( userid != -1 && courseid != -1){
                        regstatus=databaseHelper.registerFaculty(userid,courseid);
                        if(regstatus ==1){
                            Toast.makeText(UserCourseReg.this, "Success. Kindly Wait for approval from administrator", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UserCourseReg.this, "Error in registration", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(UserCourseReg.this, "Error in registration due to mismatch", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    if(userid != -1 && courseid != -1){
                        regstatus = databaseHelper.registerStudent(userid,courseid);
                        if(regstatus ==1){
                            Toast.makeText(UserCourseReg.this, "Course Successfully registered", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UserCourseReg.this, "Error in registration", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(UserCourseReg.this, "Error in registration dur to mismatch", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
            }
        });
    }
}