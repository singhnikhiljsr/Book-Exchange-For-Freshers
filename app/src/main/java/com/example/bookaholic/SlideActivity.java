package com.example.bookaholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SlideActivity extends AppCompatActivity {

    ViewPager viewPager;
    SlideViewPagerAdapter slideViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(installedAlready()){
            Intent intent = new Intent(SlideActivity.this,SignUp.class);
            startActivity(intent);
            finish();
        }
        else{
            SharedPreferences.Editor editor = (SharedPreferences.Editor) getSharedPreferences("shared",MODE_PRIVATE).edit();
            editor.putBoolean("shared",true);
            editor.commit();
        }

        setContentView(R.layout.activity_slide);

        viewPager=findViewById(R.id.viewpager);
        slideViewPagerAdapter = new SlideViewPagerAdapter(this);
        viewPager.setAdapter(slideViewPagerAdapter);
    }

    private boolean installedAlready() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean("shared",false);
        return result;
    }
}