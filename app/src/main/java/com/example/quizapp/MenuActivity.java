package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    FirebaseUser currentUser;
    FirebaseAuth mAuth;

    TextView email;

    String coins;
    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        currentUser = LoginActivity.currentuser;

        mAuth = FirebaseAuth.getInstance();
        TextView icon = (TextView) findViewById(R.id.usericon);

        Button konyhabutton = findViewById(R.id.konyhabutton);
        User user = new User(currentUser.getEmail());

        DatabaseReference userReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users/");

        email = findViewById(R.id.usersavtext);
        score = findViewById(R.id.score);

        Button etterembutton = findViewById(R.id.etterembutton);
        Button edzoterembutton = findViewById(R.id.edzoterembutton);
        Button karacsonybutton = findViewById(R.id.karacsonybutton);
        email.setText(user.getUsername());

        Query checkUser = userReference.orderByChild("username").equalTo(user.getUsername());

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    coins = snapshot.child(user.getUsername()).child("score").getValue(String.class);

                    score.setText(coins);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        konyhabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,QuizActivity.class);
                intent.putExtra("topic","Konyha");
                startActivity(intent);
            }
        });

        etterembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,QuizActivity.class);
                intent.putExtra("topic","Etterem");
                startActivity(intent);
            }
        });

        karacsonybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,QuizActivity.class);
                intent.putExtra("topic","Karacsony");
                startActivity(intent);
            }
        });

        edzoterembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,QuizActivity.class);
                intent.putExtra("topic","Gym");
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