package com.study.nodejsappchat;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.study.nodejsappchat.entities.Contact;

import java.net.URISyntaxException;

public class WelcomeActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnRegister;
    private Socket mSocket;
    private String URL = "http://192.168.2.111:3000";
//    private String URL = "http://10.0.2.2:3000";

    //    private String URL = "http://192.168.1.111:3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Toast.makeText(this, "try connect to" + URL, Toast.LENGTH_SHORT).show();
            mSocket = IO.socket(URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
        System.out.println(mSocket.id());
        setContentView(R.layout.activity_welcome);
        btnLogin = findViewById(R.id.btn_Login);
        btnRegister = findViewById(R.id.btn_Register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


}