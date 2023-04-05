package com.example.quizapp;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultsActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;

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
        String correctstring = String.valueOf(correct);
        int total = getIntent().getIntExtra("total",0);
        double szazalek = (double)correct / (double)total;
        String szazalekstring = String.valueOf(szazalek);

        //TODO READ DATA FROM DATABASE AND ONLY SET VALUES IF NEW VALUES ARE HIGHER
        DatabaseReference userReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users/");



        switch (topic){
            case "Konyha":
                //Todo//TODO temp String with szazalek value
                //            //TODO user.set..lvl1besttry(temp)
                userReference.child(user.getUsername()).child("lvl1bestattempt").setValue(szazalekstring);
                            if (szazalek >= 0.8) {
                                userReference.child(user.getUsername()).child("completedlvl1").setValue("true");

                            }
            case "Etterem":
                if (szazalek >= 0.8) {
                    //Todo user.setpassedlvl2 = "true"
                }
            case "Gym" :
                if (szazalek >= 0.8) {
                    //Todo user.setpassedlvl3 = "true"
                }
            case "Karacsony" :
                if (szazalek >= 0.8) {
                    //Todo user.setpassedlvl3 = "true"
                }
            default: break;
        }

        result.setText("Jó válasz: " + correct+ " !" + "Totál %: " +szazalek);

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