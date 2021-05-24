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

import com.example.groceryfinal.R;
import com.example.groceryfinal.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class RegistrationActivity extends AppCompatActivity {
    Button signUp;
    EditText name,email,password;
    TextView signIn;
    FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        progressBar=findViewById(R.id.progessbar);
        progressBar.setVisibility(View.GONE);

        signUp = findViewById(R.id.reg_btn);
        email = findViewById(R.id.email_reg);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password_reg);
        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                progressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    private void createUser() {
        String userName=name.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();


        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Tên người dùng không được trống", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email không được trống", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Mật khẩu không được trống", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length() < 6){
            Toast.makeText(this, "Mật khẩu phải có đội dài lớn hơn 6 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }

        //create User
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            UserModel userModel= new UserModel(userName,userEmail,userPassword);
                            String id=task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(userModel);

                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(RegistrationActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, "Lỗi111"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    }
