package com.pr.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class DashboardActivity extends AppCompatActivity {

    CardView searchbtn;
    CardView profilebtn;
    CardView logoutbtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();
        searchbtn = findViewById(R.id.searchbar);
        profilebtn = findViewById(R.id.searchbar);
        logoutbtn = findViewById(R.id.logoutbar);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userLogout();
            }
        });

    }

    private void userLogout() {

        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

}