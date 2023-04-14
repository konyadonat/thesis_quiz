package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ResultsActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;

    String score;
    FirebaseAuth mAuth;
    Button resultsButton;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mAuth = FirebaseAuth.getInstance();
        String topic =  getIntent().getStringExtra("topic");
        firebaseUser = LoginActivity.currentuser;
        User user = new User(mAuth.getCurrentUser().getEmail());
        result = findViewById(R.id.correctanswersresult);
        int correct = getIntent().getIntExtra("correct",0);
        int total = getIntent().getIntExtra("total",0);
        double szazalek = ((double)correct / (double)total)*100;
        int szazalekint = (int)Math.round(szazalek);
        String szazalekintstring = String.valueOf(szazalekint);
        DatabaseReference userReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users/");
        Query checkUser = userReference.orderByChild("username").equalTo(user.getUsername());
        DatabaseReference levelReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Levels/");
        Query checkLevel= levelReference.orderByChild("username").equalTo(user.getUsername());



        switch (topic){
            case "Konyha":
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            score = snapshot.child(user.getUsername()).child("score").getValue(String.class);
                            int scoreint = Integer.parseInt(score);
                            scoreint+=correct;
                            score = String.valueOf(scoreint);
                            userReference.child(user.getUsername()).child("score").setValue(score);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                checkLevel.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String besttrylvl1 = snapshot.child(user.getUsername()).child("lvl1bestattempt").getValue(String.class);
                            int besttrylvl1int = Integer.parseInt(besttrylvl1);

                            if (besttrylvl1int<szazalekint){
                                levelReference.child(user.getUsername()).child("lvl1bestattempt").setValue(szazalekintstring);
                            }

                            if (szazalekint >= 80) {
                                levelReference.child(user.getUsername()).child("completedlvl1").setValue("true");

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case "Etterem":
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            score = snapshot.child(user.getUsername()).child("score").getValue(String.class);
                            int scoreint = Integer.parseInt(score);
                            scoreint+=correct;
                            score = String.valueOf(scoreint);
                            userReference.child(user.getUsername()).child("score").setValue(score);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                checkLevel.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String besttrylvl2 = snapshot.child(user.getUsername()).child("lvl2bestattempt").getValue(String.class);
                            int besttrylvl2int = Integer.parseInt(besttrylvl2);

                            if (besttrylvl2int<szazalekint){
                                levelReference.child(user.getUsername()).child("lvl2bestattempt").setValue(szazalekintstring);
                            }

                            if (szazalekint >= 80) {
                                levelReference.child(user.getUsername()).child("completedlvl2").setValue("true");

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case "Gym" :
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            score = snapshot.child(user.getUsername()).child("score").getValue(String.class);
                            int scoreint = Integer.parseInt(score);
                            scoreint+=correct;
                            score = String.valueOf(scoreint);
                            userReference.child(user.getUsername()).child("score").setValue(score);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                checkLevel.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String besttrylvl3 = snapshot.child(user.getUsername()).child("lvl3bestattempt").getValue(String.class);
                            int besttrylvl3int = Integer.parseInt(besttrylvl3);

                            if (besttrylvl3int<szazalekint){
                                levelReference.child(user.getUsername()).child("lvl3bestattempt").setValue(szazalekintstring);
                            }

                            if (szazalekint >= 80) {
                                levelReference.child(user.getUsername()).child("completedlvl3").setValue("true");

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case "Karacsony" :
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            score = snapshot.child(user.getUsername()).child("score").getValue(String.class);
                            int scoreint = Integer.parseInt(score);
                            scoreint+=correct;
                            score = String.valueOf(scoreint);
                            userReference.child(user.getUsername()).child("score").setValue(score);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                checkLevel.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String besttrylvl4 = snapshot.child(user.getUsername()).child("lvl4bestattempt").getValue(String.class);
                            int besttrylvl4int = Integer.parseInt(besttrylvl4);

                            if (besttrylvl4int<szazalekint){
                                levelReference.child(user.getUsername()).child("lvl4bestattempt").setValue(szazalekintstring);
                            }

                            if (szazalekint >= 80) {
                                levelReference.child(user.getUsername()).child("completedlvl4").setValue("true");

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
        }

        result.setText("Jó válasz: "+correct);

        resultsButton = findViewById(R.id.resultsbutton);

        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}