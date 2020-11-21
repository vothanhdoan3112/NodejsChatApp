package com.study.nodejsappchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.study.nodejsappchat.entities.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {


    private String verificationId;
    private String phoneNum;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText txtCode;
    private Button verify, reSend,btnBack;
    private User user;
    private String URL = "http://10.0.2.2:3000";


    private Socket mSocket;
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
        setContentView(R.layout.activity_verify_phone);

        mSocket.connect();

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbar);
        txtCode = findViewById(R.id.editTextCode);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyPhoneActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        user = (User)getIntent().getSerializableExtra("user");
        phoneNum = user.getPhoneNum();
        sendVerificationCode(phoneNum);
        verify = findViewById(R.id.btnVerify);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = txtCode.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {

                    txtCode.setError("Nhập mã xác thực...");
                    txtCode.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });

    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            try {
                                mSocket.emit("regAcc", new JSONObject(new Gson().toJson(user)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            mSocket.on("IsRegSuccess", new Emitter.Listener() {
                                @Override
                                public void call(final Object... args) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            boolean flag = (Boolean) args[0];
                                            System.out.println(flag);
                                            if (flag) {
                                                Toast.makeText(VerifyPhoneActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(VerifyPhoneActivity.this, HomeActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                intent.putExtra("logUser",user);
                                                startActivity(intent);
                                            }else{
                                                Toast.makeText(VerifyPhoneActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                                }
                            });

                        } else {
                            Toast.makeText(VerifyPhoneActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String phoneNum) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84" + phoneNum,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack);
}

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                txtCode.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

}
