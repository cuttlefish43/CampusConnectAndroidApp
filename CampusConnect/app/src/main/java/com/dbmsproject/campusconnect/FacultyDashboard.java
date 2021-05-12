package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FacultyDashboard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fbtn_announcements,fbtn_materials,fbtn_disscuss, fbtn_newmessage,fbtn_testnotify;
    TextView tv_sctitle, tv_sc_des;
    int userid;
    int courseid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Faculty Dashboard");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);
        tv_sctitle= findViewById(R.id.tv_sctitle);
        tv_sc_des = findViewById(R.id.tv_sc_des);
        fbtn_testnotify= findViewById(R.id.fbtn_testnotify);
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
        DatabaseHelper databaseHelper= new DatabaseHelper(FacultyDashboard.this);
        List<Oothers> annList=databaseHelper.getAllAnnouncements(userid,courseid);
        myAdapter = new FacultyDashAdapter(annList,userid,courseid,0);
        recyclerView.setAdapter(myAdapter);
        DatabaseHelper db= new DatabaseHelper(FacultyDashboard.this);
        OCourses obj = db.getCourse(courseid);
        tv_sctitle.setText(obj.getCoursename());
        tv_sc_des.setText(obj.getDescription());
        fbtn_announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int purpose =0; // i am setting 0 as idenfier for list of type announcemnts. for adapter settings
                DatabaseHelper databaseHelper= new DatabaseHelper(FacultyDashboard.this);
                List<Oothers>annList=databaseHelper.getAllAnnouncements(userid,courseid);
                myAdapter = new FacultyDashAdapter(annList,userid,courseid,purpose);
                recyclerView.setAdapter(myAdapter);




            }
        });
        fbtn_materials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int purpose =1; // i am setting 1 as idenfier for list of type attachments.
                DatabaseHelper databaseHelper= new DatabaseHelper(FacultyDashboard.this);
                List<Oothers>materialList=databaseHelper.getAllMaterials(userid,courseid);
                myAdapter = new FacultyDashAdapter(materialList,userid,courseid, purpose);
                recyclerView.setAdapter(myAdapter);
                Intent it = new Intent(FacultyDashboard.this,UploadMaterial.class);
                it.putExtra("courseid",courseid);
                startActivity(it);





            }
        });
        fbtn_disscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int purpose =2; // i am setting 3 as idenfier for list of type discussion.
                DatabaseHelper databaseHelper= new DatabaseHelper(FacultyDashboard.this);
                List<Oothers>dissList=databaseHelper.getAlldisscussions(userid,courseid);
                myAdapter = new FacultyDashAdapter(dissList,userid,courseid, purpose);
                recyclerView.setAdapter(myAdapter);



            }
        });

        // not a list type
        fbtn_testnotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper1 = new DatabaseHelper(FacultyDashboard.this);
                String [] Studentemails = databaseHelper1.getStudentEmails(courseid).toArray(new String[0]);
                /*
                for(int x=0;x<Studentemails.length;x++){
                    System.out.println(Studentemails[x]);
                }

                 */
                ;
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,Studentemails);
                intent.putExtra(Intent.EXTRA_SUBJECT,"course"+ courseid+" Test");
                intent.putExtra(Intent.EXTRA_TEXT,"Tomorrow is 1 hour test for the course. Don't miss.");
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }

            }
        });



    }
}