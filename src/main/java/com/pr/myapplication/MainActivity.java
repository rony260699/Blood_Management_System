package com.pr.myapplication;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText psswrd;
    Button login;
    Button create_acc;
    Button emergency;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        psswrd = findViewById(R.id.passwd);
        login = findViewById(R.id.loginbtn);
        create_acc = findViewById(R.id.gosignupbtn);
        emergency = findViewById(R.id.emergencybtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                /*
                if(username.getText().toString().equals("user") && passward.getText().toString().equals("1234")){
                    Toast.makeText(MainActivity.this, "login successfull", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                }
                */
                 userLogin();
            }
        });

        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                        startActivity(intent);

            }
        });

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });



    }

    private void userLogin() {
        String email = username.getText().toString().trim();
        String password = psswrd.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            username.setError("Enter an email address");
            username.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            username.setError("Enter a valid email address");
            username.requestFocus();
            return;
        }

        //checking the validity of the password
        if(email.isEmpty())
        {
            psswrd.setError("Enter a password");
            psswrd.requestFocus();
            return;
        }
        if(password.length()<6){
            psswrd.setError("Minimum length should be 6");
            psswrd.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}