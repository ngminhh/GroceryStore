package com.example.groceryfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.groceryfinal.MainActivity;
import com.example.groceryfinal.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progessbar);
        progressBar.setVisibility(View.GONE);
        if (auth.getCurrentUser()!= null){
            progressBar.setVisibility(View.VISIBLE);

            Toast.makeText(this, "Vui lòng đợi trong giây lát", Toast.LENGTH_SHORT).show();

        }

    }

    public void registration(View view) {
        startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
    }

    public void login(View view) {

        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }
}