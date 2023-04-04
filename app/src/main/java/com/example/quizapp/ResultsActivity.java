package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    Button resultsButton;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        result = findViewById(R.id.correctanswersresult);
        int correct = getIntent().getIntExtra("correct",0);
        int total = getIntent().getIntExtra("total",0);

        double szazalek = (double)correct / (double)total;
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