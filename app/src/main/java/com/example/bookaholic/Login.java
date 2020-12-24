package com.example.bookaholic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText  memail, mpassword;
    Button btnLogin;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    TextView newUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        newUser = findViewById(R.id.newUser);

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = memail.getText().toString();
                String password = mpassword.getText().toString();

                if(email.isEmpty()){
                    memail.setError("Email is Required");
                    //memail.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    mpassword.setError("Password is Required");
                    return;
                }

                if(password.length()<6){
                    mpassword.setError("Password length should be greater than 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(Login.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                }



                });







            }
        });
    }
}