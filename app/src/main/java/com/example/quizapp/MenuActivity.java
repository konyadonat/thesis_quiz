package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();
        TextView icon = (TextView) findViewById(R.id.usericon);

        Button konyhabutton = findViewById(R.id.konyhabutton);

        //TODO ETTEREM, GYM, KARACSONY
        Button etterembutton = findViewById(R.id.etterembutton);
        Button edzoterembutton = findViewById(R.id.edzoterembutton);
        Button karacsonybutton = findViewById(R.id.karacsonybutton);

        konyhabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,QuizActivity.class);
                intent.putExtra("topic","konyha");
                startActivity(intent);
            }
        });


        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}