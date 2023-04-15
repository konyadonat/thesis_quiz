package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        currentUser = LoginActivity.currentuser;

        Button logout = findViewById(R.id.profilelogutbutton);
        Button backbutton = findViewById(R.id.profilebackbutton);
        EditText emailet = findViewById(R.id.profileemail);
        EditText scoreet = findViewById(R.id.profilescore);

        ImageView lvl1badgeimage = findViewById(R.id.level1badge);
        ImageView lvl2badgeimage = findViewById(R.id.level2badge);
        ImageView lvl3badgeimage = findViewById(R.id.level3badge);
        ImageView lvl4badgeimage = findViewById(R.id.level4badge);

        ImageView score50badgeimage = findViewById(R.id.score50);
        ImageView score100badgeimage = findViewById(R.id.score100);

        User user = new User(mAuth.getCurrentUser().getEmail());

        DatabaseReference userReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users/");

        Query checkUser = userReference.orderByChild("username").equalTo(user.getUsername());

        DatabaseReference badgeRerence = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Badges/");

        Query checkBadge = badgeRerence.orderByChild("username").equalTo(user.getUsername());

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String email = snapshot.child(user.getUsername()).child("email").getValue(String.class);
                    String score = snapshot.child(user.getUsername()).child("score").getValue(String.class);
                    scoreet.setText(score);
                    emailet.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        checkBadge.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String lvl1badge = snapshot.child(user.getUsername()).child("completedlevel1").getValue(String.class);
                    String lvl2badge = snapshot.child(user.getUsername()).child("completedlevel2").getValue(String.class);
                    String lvl3badge = snapshot.child(user.getUsername()).child("completedlevel3").getValue(String.class);
                    String lvl4badge = snapshot.child(user.getUsername()).child("completedlevel4").getValue(String.class);

                    String score50badge = snapshot.child(user.getUsername()).child("score50").getValue(String.class);
                    String score100badge = snapshot.child(user.getUsername()).child("score100").getValue(String.class);


                    if (Objects.equals(lvl1badge, "true")){
                        lvl1badgeimage.setVisibility(View.VISIBLE);
                    }

                    if (Objects.equals(lvl2badge, "true")){
                        lvl2badgeimage.setVisibility(View.VISIBLE);
                    }
                    if (Objects.equals(lvl3badge, "true")){
                        lvl3badgeimage.setVisibility(View.VISIBLE);
                    }
                    if (Objects.equals(lvl4badge, "true")){
                        lvl4badgeimage.setVisibility(View.VISIBLE);
                    }

                    if (Objects.equals(score50badge, "true")){
                        score50badgeimage.setVisibility(View.VISIBLE);
                    }
                    if (Objects.equals(score100badge, "true")){
                        score100badgeimage.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}