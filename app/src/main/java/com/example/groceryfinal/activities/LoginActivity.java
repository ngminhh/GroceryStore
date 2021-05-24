package com.example.groceryfinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groceryfinal.MainActivity;
import com.example.groceryfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    Button signIn;
    EditText email,password;
    TextView signUp;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth=FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progessbar);
        progressBar.setVisibility(View.GONE);


        signIn = findViewById(R.id.login_btn);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signUp = findViewById(R.id.sign_up);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                progressBar.setVisibility(View.VISIBLE);


            }
        });
    }

    private void loginUser() {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();


        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email không được trống", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Mật khẩu không được trống", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length() < 6){
            Toast.makeText(this, "Mật khẩu phải có độ dài lớn hơn 6 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }
        //login user
        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()
                        ){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "loi112"+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    }



