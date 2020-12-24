package com.example.bookaholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Books extends AppCompatActivity {

    CardView phy,chem,maths,c,algo,java,ece,eed_mechanics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        phy=findViewById(R.id.physics);
        chem=findViewById(R.id.chemistry);
        maths=findViewById(R.id.maths);
        c=findViewById(R.id.c);
        algo=findViewById(R.id.algo);
        java=findViewById(R.id.java);
        ece=findViewById(R.id.ece);
        eed_mechanics=findViewById(R.id.eed_mechanics);

        phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Physics.class);
                startActivity(intent);
            }
        });

        chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Chemistry.class);
                startActivity(intent);
            }
        });
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Maths.class);
                startActivity(intent);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),C.class);
                startActivity(intent);
            }
        });

        algo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Algorithms.class);
                startActivity(intent);
            }
        });

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Java.class);
                startActivity(intent);
            }
        });

        ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Electronics.class);
                startActivity(intent);
            }
        });

        eed_mechanics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EED_Mechanics.class);
                startActivity(intent);
            }
        });
    }
}