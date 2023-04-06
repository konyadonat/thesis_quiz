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
import java.util.Objects;

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
        User user = new User(mAuth.getCurrentUser().getEmail());

        DatabaseReference userReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users/");

        DatabaseReference levelsReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Levels/");

        email = findViewById(R.id.usersavtext);
        score = findViewById(R.id.score);

        Button etterembutton = findViewById(R.id.etterembutton);
        Button edzoterembutton = findViewById(R.id.edzoterembutton);
        Button karacsonybutton = findViewById(R.id.karacsonybutton);
        email.setText(user.getUsername());

        Query checklevel = levelsReference.orderByChild("username").equalTo(user.getUsername());

        checklevel.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String completedlevel1 = snapshot.child(user.getUsername()).child("completedlvl1").getValue(String.class);
                    String completedlevel2 = snapshot.child(user.getUsername()).child("completedlvl2").getValue(String.class);
                    String completedlevel3 = snapshot.child(user.getUsername()).child("completedlvl3").getValue(String.class);

                    if (Objects.equals(completedlevel1, "false")){
                        etterembutton.setClickable(false);
                        etterembutton.setText("Először csináld meg az első szintet!");
                    }
                    if (Objects.equals(completedlevel2, "false")){
                        edzoterembutton.setClickable(false);
                        edzoterembutton.setText("Először csináld meg a második szintet!");
                    }
                    if (Objects.equals(completedlevel3, "false")){
                        karacsonybutton.setClickable(false);
                        karacsonybutton.setText("Először csináld meg a harmadik szintet!");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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
                recreate();
                finish();

            }
        });

        etterembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,QuizActivity.class);
                intent.putExtra("topic","Etterem");
                startActivity(intent);
                recreate();
                finish();
            }
        });

        karacsonybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,QuizActivity.class);
                intent.putExtra("topic","Karacsony");
                startActivity(intent);
                recreate();
                finish();
            }
        });

        edzoterembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,QuizActivity.class);
                intent.putExtra("topic","Gym");
                startActivity(intent);
                recreate();
                finish();
            }
        });



        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,ProfileActivity.class);
                startActivity(intent);
                recreate();
            }
        });
    }

}