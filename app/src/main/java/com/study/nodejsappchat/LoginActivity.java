package com.study.nodejsappchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.study.nodejsappchat.entities.User;

import org.json.JSONObject;

import java.net.URISyntaxException;

public class LoginActivity extends AppCompatActivity {
    private Button btnBack,btnNext;
    private EditText txtPhoneNum,txtPassword;
    private Socket mSocket;
    private String URL = "http://192.168.1.111:3000";
    {
        try {
            mSocket = IO.socket(URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSocket.connect();
        txtPhoneNum = findViewById(R.id.txtPhone);
        txtPassword = findViewById(R.id.txtPassword);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = txtPhoneNum.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                mSocket.emit("login", phoneNum, password);

                mSocket.on("IsLoginSuccess", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                JSONObject JsonUser = (JSONObject) args[0];
                                Toast.makeText(LoginActivity.this,"ok",Toast.LENGTH_LONG).show();
                                if (JsonUser !=null) {
                                    User loginUser = new Gson().fromJson(JsonUser.toString(),User.class);
                                   // System.out.println(JsonUser);
                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    intent.putExtra("logUser",loginUser);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(LoginActivity.this,"Sai thông tin đăng nhập",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });
            }
        });
    }
}