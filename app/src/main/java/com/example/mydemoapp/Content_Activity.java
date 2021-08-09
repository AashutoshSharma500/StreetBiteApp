package com.example.mydemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Content_Activity extends AppCompatActivity {

    Button logout,btn_breakfast,btn_lunch,btn_dinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Content_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn_breakfast=findViewById(R.id.btn_breakfast);
        btn_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Content_Activity.this,Breakfast_Activity.class);
                startActivity(intent);
            }
        });
        btn_lunch=findViewById(R.id.btn_lunch);
        btn_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Content_Activity.this, Lunch_Activity.class);
                startActivity(intent);
            }
        });

        btn_dinner=findViewById(R.id.btn_dinner);
        btn_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Content_Activity.this, Dinner_Activity.class);
                startActivity(intent);
            }
        });

    }
}