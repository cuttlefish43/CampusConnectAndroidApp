package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdminDashboard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;


    Button btn_ad_allc,btn_ad_allusers,btn_ad_apv;
    RecyclerView lv_items;
    FloatingActionButton fbtn_add,fbtn_announce;
    int purpose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        recyclerView=findViewById(R.id.rv_ad_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        btn_ad_allc=findViewById(R.id.btn_ad_allc);
        btn_ad_allusers=findViewById(R.id.btn_ad_allusers);
        btn_ad_apv=findViewById(R.id.btn_ad_apv);
        fbtn_add= findViewById(R.id.fbtn_add);

        lv_items=findViewById(R.id.rv_ad_list);
        DatabaseHelper databaseHelper = new DatabaseHelper(AdminDashboard.this);
        List<Ousers> annList=databaseHelper.allUsersforAdmin();
        myAdapter = new AdminDashAdapter(annList,0);
        recyclerView.setAdapter(myAdapter);




        btn_ad_allusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose=0;
                DatabaseHelper databaseHelper = new DatabaseHelper(AdminDashboard.this);
                List<Ousers> annList=databaseHelper.allUsersforAdmin();
                myAdapter = new AdminDashAdapter(annList,purpose);
                recyclerView.setAdapter(myAdapter);
            }
        });
        btn_ad_allc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose =1;
                DatabaseHelper databaseHelper = new DatabaseHelper(AdminDashboard.this);
                List<OCourses> annList=databaseHelper.allCoursesforAdmin();
                myAdapter = new AdminDashAdapter(annList,purpose);
                recyclerView.setAdapter(myAdapter);
            }
        });
        btn_ad_apv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose =2;
                DatabaseHelper dbhelper= new DatabaseHelper(getApplicationContext());
                List<Approval> approvalist= dbhelper.getAllApprovals();
                myAdapter = new AdminDashAdapter(approvalist,purpose);
                recyclerView.setAdapter(myAdapter);

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