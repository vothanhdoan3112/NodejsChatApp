package com.study.nodejsappchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import com.study.nodejsappchat.entities.User;



public class RegisterActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnReg;
    private EditText txtUsername, txtPhoneNum, txtPassword, txtRePassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        btnBack = findViewById(R.id.btnBack);
        btnReg = findViewById(R.id.btnNext);
        txtUsername = findViewById(R.id.txtUserName);
        txtPhoneNum = findViewById(R.id.txtPhone);
        txtPassword = findViewById(R.id.txtPassword);
        txtRePassword = findViewById(R.id.txtRePassword);



        /***/
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String phoneNum = txtPhoneNum.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String rePassword = txtRePassword.getText().toString().trim();
                if(isValidate(username,phoneNum,password,rePassword)){
                    Intent intent = new Intent(RegisterActivity.this, VerifyPhoneActivity.class);
                    User user = new User(phoneNum,username,password);

                    intent.putExtra("user", user);

                    startActivity(intent);
                }
            }
        });


    }

    private boolean isValidate(String userName, String phoneNum,String password,String rePassword) {
        if(userName.isEmpty()){
            return false;
        }
        if (phoneNum.isEmpty() ||phoneNum.length() > 10 || phoneNum.length() < 9) {
            return false;
        }
        if (password.length() > 30 || password.length() < 1) {
            return false;
        }
        if (!rePassword.equals(password)) {
            return false;
        }
        return true;
    }
}