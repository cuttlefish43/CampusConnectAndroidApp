package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends Activity {
    EditText et_adminname, et_adminpass;
    Button btn_adminsignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        et_adminname=findViewById(R.id.et_adminname);
        et_adminpass=findViewById(R.id.et_adminpass);
        btn_adminsignin=findViewById(R.id.btn_adminsignin);
        btn_adminsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(et_adminname.getText().toString().equals("Admin123") && et_adminpass.getText().toString().equals("pass123")){
                        Intent it= new Intent(getApplicationContext(),AdminDashboard.class);
                        startActivity(it);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Complete all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}