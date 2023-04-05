package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        firebaseUser = mAuth.getCurrentUser();
        result = findViewById(R.id.correctanswersresult);
        int correct = getIntent().getIntExtra("correct",0);
        int total = getIntent().getIntExtra("total",0);
        double szazalek = (double)correct / (double)total;

        if (topic == "Konyha") {

        }

        switch (topic){
            case "Konyha":
                //Todo//TODO temp String with szazalek value
                //            //TODO user.set..lvl1besttry(temp)
                            if (szazalek >= 0.8) {
                                //Todo user.setpassedlvl1 = "true"
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
                finish();
            }
        });

    }
}