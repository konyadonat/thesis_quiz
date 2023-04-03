package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    //TODO FRONTEND
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        result = findViewById(R.id.correctanswersresult);
        int correct = getIntent().getIntExtra("correct",0);
        result.setText("Jó válasz: " + correct+ " !");


    }
}