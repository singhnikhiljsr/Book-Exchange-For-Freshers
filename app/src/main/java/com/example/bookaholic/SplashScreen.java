package com.example.bookaholic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(getApplicationContext() , SlideActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },3000);

                Intent intent = new Intent(getApplicationContext() , SlideActivity.class);
                startActivity(intent);
                finish();

    }
}