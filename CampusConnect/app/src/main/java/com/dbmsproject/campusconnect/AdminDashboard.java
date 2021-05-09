package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdminDashboard extends AppCompatActivity {
    Button btn_ad_allc,btn_ad_allusers,btn_ad_apv;
    ListView lv_items;
    FloatingActionButton fbtn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        btn_ad_allc=findViewById(R.id.btn_ad_allc);
        btn_ad_allusers=findViewById(R.id.btn_ad_allusers);
        btn_ad_apv=findViewById(R.id.btn_ad_apv);
        fbtn_add= findViewById(R.id.fbtn_add);
        lv_items=findViewById(R.id.lv_items);
        btn_ad_allusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbhelper= new DatabaseHelper(getApplicationContext());
                List<Ousers> userlist= dbhelper.allUsers();
                ArrayAdapter useradapter= new ArrayAdapter<Ousers>(AdminDashboard.this,android.R.layout.simple_expandable_list_item_1,userlist);
                lv_items.setAdapter(useradapter);
            }
        });
        btn_ad_allc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbhelper= new DatabaseHelper(getApplicationContext());
                List<OCourses> courseslist= dbhelper.getAllCourses();
                ArrayAdapter useradapter= new ArrayAdapter<OCourses>(AdminDashboard.this,android.R.layout.simple_expandable_list_item_1,courseslist);
                lv_items.setAdapter(useradapter);
            }
        });
        btn_ad_apv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fbtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this,addNewCourse.class));
            }
        });



    }

}