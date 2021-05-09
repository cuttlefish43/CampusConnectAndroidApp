package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseDashboard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Button btn_regcourses,btn_allcourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Course Dashboard");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_dashboard);
        // getting current user object
        CurrentUser currentUser= (CurrentUser)getIntent().getSerializableExtra("CurrentUser");

        recyclerView= findViewById(R.id.rv_courses);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // buttons
        btn_regcourses=findViewById(R.id.btn_regcourses);
        btn_allcourses=findViewById(R.id.btn_allcourses);

        try{
            DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
            List<OCourses> CoursesList = dbhelper.getAllCourses();
            System.out.println(CoursesList.size());
            //Toast.makeText(this, CoursesList.size(), Toast.LENGTH_SHORT).show();
            myAdapter = new RvAdapter(CoursesList); //has parameter - a list, eg: MyAdapter(numberList);
            recyclerView.setAdapter(myAdapter);
        }catch (Exception e){
            System.out.println("receive error ");
        }
        btn_regcourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
                    List<OCourses> CoursesList = dbhelper.getRegCourses( currentUser.id); // registered courses for current id
                    System.out.println(CoursesList.size());
                    //Toast.makeText(this, CoursesList.size(), Toast.LENGTH_SHORT).show();
                    myAdapter = new RvAdapter(CoursesList); //has parameter - a list, eg: MyAdapter(numberList);
                    recyclerView.setAdapter(myAdapter);
                }catch (Exception e){
                    System.out.println("receive error ");
                }

            }
        });
        btn_allcourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
                    List<OCourses> CoursesList = dbhelper.getAllCourses();
                    System.out.println(CoursesList.size());
                    //Toast.makeText(this, CoursesList.size(), Toast.LENGTH_SHORT).show();
                    myAdapter = new RvAdapter(CoursesList); //has parameter - a list, eg: MyAdapter(numberList);
                    recyclerView.setAdapter(myAdapter);
                }catch (Exception e){
                    System.out.println("receive error ");
                }

            }
        });
    }
}