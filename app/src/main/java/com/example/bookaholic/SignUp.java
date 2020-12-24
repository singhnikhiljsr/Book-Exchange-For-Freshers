package com.example.bookaholic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mfullName, memail, mpassword, mphone;
    Button btnSignUp;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    TextView alreadyRegistered;
    FirebaseFirestore firebaseFirestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mfullName = findViewById(R.id.fullName);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mphone = findViewById(R.id.phone);
        btnSignUp = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        alreadyRegistered = findViewById(R.id.newUser);
        firebaseFirestore = FirebaseFirestore.getInstance();


        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });

        if(firebaseAuth.getCurrentUser()!=null){
            Intent intent = new Intent(SignUp.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = memail.getText().toString();
                final String password = mpassword.getText().toString();
                final String fullName = mfullName.getText().toString();
                final String phone = mphone.getText().toString();

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

                if(phone.length()<10){
                    mphone.setError("Invalid Phone Number");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            userID = firebaseAuth.getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);

                            Map<String,Object> user = new HashMap<>();
                            user.put("Full Name",fullName);
                            user.put("Email",email);
                            user.put("Password",password);
                            user.put("Phone",phone);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"Successfully Inserted data in Database for user: "+userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"On Failure: "+e.toString());
                                }
                            });

                            Toast.makeText(SignUp.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(SignUp.this, "Error ! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}