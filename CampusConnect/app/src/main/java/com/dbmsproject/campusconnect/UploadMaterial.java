package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UploadMaterial extends Activity {
    Button btn_upload;
    EditText et_text;
    int courseid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_material);
        btn_upload= findViewById(R.id.btn_upload);
        et_text = findViewById(R.id.et_upload);
        Intent data = getIntent();
        courseid= data.getIntExtra("courseid",-1);
        DatabaseHelper databaseHelper = new DatabaseHelper(UploadMaterial.this);
        int status = databaseHelper.addMaterial(courseid,et_text.getText().toString());
        if(status ==1){
            Toast.makeText(UploadMaterial.this, " Link uploaded successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(UploadMaterial.this, " Error uploading", Toast.LENGTH_SHORT).show();
        }
        finish();

    }
}