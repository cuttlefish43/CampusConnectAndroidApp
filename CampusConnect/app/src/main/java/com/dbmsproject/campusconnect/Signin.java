package com.dbmsproject.campusconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    Button btn_signin;
    EditText et_email,et_password,et_name;
    Switch sw_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        btn_signin= findViewById(R.id.btn_signin);
        et_email=findViewById(R.id.et_email);
        et_password= findViewById(R.id.et_password);
        et_name=findViewById(R.id.et_name);
        sw_register=findViewById(R.id.sw_register);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbhelper= new DatabaseHelper(getApplicationContext());
                if(sw_register.isChecked()){
                    int success= dbhelper.addUser(et_name.getText().toString(),et_email.getText().toString(),et_password.getText().toString());
                    if(success == 1) {
                        Toast.makeText(Signin.this, " Successfully registered", Toast.LENGTH_SHORT).show();
                    }else if(success == 2){
                        Toast.makeText(Signin.this, "Email already taken", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Signin.this, "Error registration", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    int status=dbhelper.checkUser(et_email.getText().toString(),et_password.getText().toString());
                    if(status == -1){
                        Toast.makeText(Signin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Signin.this, "Signin successfull", Toast.LENGTH_SHORT).show();
                    }
                }
                //Toast.makeText(getApplicationContext(), et_password.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}