package com.pr.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText fulNam, Email, phoNo, addrs, bldGrp, setPas;// confpas;
    Button signUpbtn;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseReference = FirebaseDatabase.getInstance().getReference("userinfo");
        mAuth = FirebaseAuth.getInstance();
        fulNam = findViewById(R.id.ful_name);
        Email = findViewById(R.id.emailid);
        phoNo = findViewById(R.id.phn_no_id);
        addrs = findViewById(R.id.address_id);
        bldGrp = findViewById(R.id.blood_grp_id);
        setPas = findViewById(R.id.set_passwd);
        //confpas = findViewById(R.id.conf_passwd);
        signUpbtn = findViewById(R.id.signupbtn_id);

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveData();
                userRegister();
            }

        });

    }

    private void saveData() {
        String name = fulNam.getText().toString().trim();
        String phn_no = phoNo.getText().toString().trim();
        String address = addrs.getText().toString().trim();
        String bld_group = bldGrp.getText().toString().trim();
        String email_adrs = Email.getText().toString().trim();
        String key = databaseReference.push().getKey();
        Userinfo userinfo = new Userinfo(name,phn_no,address,bld_group,email_adrs);
        databaseReference.child(key).setValue(userinfo);
        Toast.makeText(getApplicationContext(),"info is added",Toast.LENGTH_LONG).show();

    }

    private void userRegister() {

        String email = Email.getText().toString().trim();
        String password = setPas.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            Email.setError("Enter an email address");
            Email.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Email.setError("Enter a valid email address");
            Email.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            setPas.setError("Enter a password");
            setPas.requestFocus();
            return;
        }
        if(password.length()<6){
            setPas.setError("Minimum length should be 6");
            setPas.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Register is Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "User is already Register", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
}