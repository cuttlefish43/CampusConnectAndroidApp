package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StudentDashBoard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fbtn_announcements,fbtn_materials,fbtn_disscuss, fbtn_newmessage;
    TextView tv_sctitle, tv_sc_des;
    int userid;
    int courseid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.setTitle("Student Dashboard");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash_board);

        tv_sctitle= findViewById(R.id.tv_sctitle);
        tv_sc_des = findViewById(R.id.tv_sc_des);
        fbtn_announcements=findViewById(R.id.fbtn_fannouncements);
        fbtn_disscuss=findViewById(R.id.fbtn_fdisscuss);
        fbtn_materials=findViewById(R.id.fbtn_fmaterials);
        fbtn_newmessage= findViewById(R.id.fbtn_newmessage);
        Intent getdata= getIntent();
        userid= getdata.getIntExtra("userid",-1);
        courseid = getdata.getIntExtra("courseid",-1);
        recyclerView=findViewById(R.id.rv_fc_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        /* Default will be announcements*/
        DatabaseHelper databaseHelper= new DatabaseHelper(StudentDashBoard.this);
        List<Oothers>annList=databaseHelper.getAllAnnouncements(userid,courseid);
        myAdapter = new StudentDashAdapter(annList,userid,courseid,0);
        recyclerView.setAdapter(myAdapter);
        DatabaseHelper db= new DatabaseHelper(StudentDashBoard.this);
        OCourses obj = db.getCourse(courseid);
        tv_sctitle.setText(obj.getCoursename());
        tv_sc_des.setText(obj.getDescription());

        fbtn_announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int purpose =0; // i am setting 0 as idenfier for list of type announcemnts. for adapter settings
                    DatabaseHelper databaseHelper= new DatabaseHelper(StudentDashBoard.this);
                    List<Oothers>annList=databaseHelper.getAllAnnouncements(userid,courseid);
                    myAdapter = new StudentDashAdapter(annList,userid,courseid,purpose);
                    recyclerView.setAdapter(myAdapter);




            }
        });
        fbtn_materials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int purpose =1; // i am setting 1 as idenfier for list of type attachments.
                    DatabaseHelper databaseHelper= new DatabaseHelper(StudentDashBoard.this);
                    List<Oothers>materialList=databaseHelper.getAllMaterials(userid,courseid);
                    myAdapter = new StudentDashAdapter(materialList,userid,courseid, purpose);
                    recyclerView.setAdapter(myAdapter);




            }
        });
        fbtn_disscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int purpose =2; // i am setting 3 as idenfier for list of type discussion.
                    DatabaseHelper databaseHelper= new DatabaseHelper(StudentDashBoard.this);
                    List<Oothers>dissList=databaseHelper.getAlldisscussions(userid,courseid);
                    myAdapter = new StudentDashAdapter(dissList,userid,courseid, purpose);
                    recyclerView.setAdapter(myAdapter);




            }
        });
        fbtn_newmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(StudentDashBoard.this,StudentsendMessage.class);
                it.putExtra("muserid",userid);
                it.putExtra("mcourseid",courseid);
                startActivity(it);
            }
        });

        myAdapter.notifyDataSetChanged();
    }
}