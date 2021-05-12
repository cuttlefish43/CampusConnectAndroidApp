package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class StudentsendMessage extends Activity {
    EditText et_message;
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentsend_message);
        Intent data= getIntent();
        int userid=data.getIntExtra("muserid",-1);
        int courseid =data.getIntExtra("mcourseid",-1);
        et_message= findViewById(R.id.et_typechat);
        btn_send= findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(StudentsendMessage.this);
                    int status= databaseHelper.sendMessage(userid,courseid,et_message.getText().toString());
                    if(status == 1){
                        Toast.makeText(StudentsendMessage.this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(StudentsendMessage.this, "Error sending Message", Toast.LENGTH_SHORT).show();
                    }

                    finish();
            }
        });
    }
}