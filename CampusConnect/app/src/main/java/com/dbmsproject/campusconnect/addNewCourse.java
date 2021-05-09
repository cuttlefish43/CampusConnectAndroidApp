package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addNewCourse extends Activity {
    Button btn_submit;
    EditText et_newcoursename,et_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
        btn_submit=findViewById(R.id.btn_submit);
        et_description=findViewById(R.id.et_description);
        et_newcoursename=findViewById(R.id.et_newcoursename);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OCourses newcourse= new OCourses(-1,et_newcoursename.getText().toString(),et_description.getText().toString());
                DatabaseHelper dbhelper= new DatabaseHelper(addNewCourse.this);
                int status= dbhelper.addCourse(newcourse);
                if(status == -1){
                    Toast.makeText(addNewCourse.this, "Insert Error. Try Again", Toast.LENGTH_SHORT).show();

                }else if(status == 0){
                    Toast.makeText(addNewCourse.this, " Course Name already present", Toast.LENGTH_SHORT).show();
                }else if(status ==1){
                    Toast.makeText(addNewCourse.this, " Course added Successfully", Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });
    }
}