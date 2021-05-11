package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adannounce extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adannounce);
        Button btn_ann;
        EditText et_ann;
        btn_ann= findViewById(R.id.btn_annun);
        et_ann= findViewById(R.id.et_announ);
        Intent cadta= getIntent();
        int courseid= cadta.getIntExtra("cid",-1);
        System.out.println(courseid);
        if(courseid != -1){
            btn_ann.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(Adannounce.this);
                    int status= databaseHelper.addAnnouncement(courseid,et_ann.getText().toString());
                    if(status ==1){
                        Toast.makeText(Adannounce.this, "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Adannounce.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
        //finish();
    }

}