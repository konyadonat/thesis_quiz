package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private TextView questions;
    private TextView question;
    TextView username;
    TextView topic;

    private Button option1,option2,option3,option4;

    private Button submit;

    private Timer quizTimer;

    FirebaseUser currentUser;
    FirebaseAuth mAuth;

    private int totalTimeInMisn = 0;

    private  int seconds = 59;

    private  List<QuestionList> questionLists = new ArrayList<>();
    String getTopic;

    private int currentQuestionPosition = 0;

    private String selectedOption = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        currentUser = LoginActivity.currentuser;
        mAuth = FirebaseAuth.getInstance();

        User user = new User(mAuth.getCurrentUser().getEmail());


        TextView timer = findViewById(R.id.timer);
        question = findViewById(R.id.question);
        questions = findViewById(R.id.progresstextview);
        getTopic = getIntent().getStringExtra("topic");


        username = findViewById(R.id.usersavtext);
        topic = findViewById(R.id.topictv);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        submit = findViewById(R.id.submitbutton);

        ProgressDialog progressDialog = new ProgressDialog(QuizActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Betöltés....");
        progressDialog.show();

        topic.setText(getTopic);
        username.setText(user.getUsername());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Topics");


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.child(getTopic).getChildren()){
                    String getQuestion = dataSnapshot.child("question").getValue(String.class);
                    String getOption1 = dataSnapshot.child("option1").getValue(String.class);
                    String getOption2 = dataSnapshot.child("option2").getValue(String.class);
                    String getOption3 = dataSnapshot.child("option3").getValue(String.class);
                    String getOption4 = dataSnapshot.child("option4").getValue(String.class);
                    String getAnswer = dataSnapshot.child("answer").getValue(String.class);

                    QuestionList questionList = new QuestionList(getQuestion, getOption1, getOption2, getOption3, getOption4, getAnswer);
                    questionLists.add(questionList);
                }

                progressDialog.hide();

                questions.setText((currentQuestionPosition+1)+"/"+questionLists.size());
                question.setText(questionLists.get(0).getQuestion());
                option1.setText(questionLists.get(0).getOption1());
                option2.setText(questionLists.get(0).getOption2());
                option3.setText(questionLists.get(0).getOption3());
                option4.setText(questionLists.get(0).getOption4());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        startTimer(timer);


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()) {
                 selectedOption = option1.getText().toString();

                 option1.setBackgroundResource(R.drawable.round_back_red);
                 option1.setTextColor(Color.WHITE);
                 revealAnsw();

                 questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOption);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()) {
                    selectedOption = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.round_back_red);
                    option2.setTextColor(Color.WHITE);

                    revealAnsw();

                    questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOption);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()) {
                    selectedOption = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.round_back_red);
                    option3.setTextColor(Color.WHITE);

                    revealAnsw();

                    questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOption);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()) {
                    selectedOption = option4.getText().toString();

                    option4.setBackgroundResource(R.drawable.round_back_red);
                    option4.setTextColor(Color.WHITE);

                    revealAnsw();

                    questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOption);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOption.isEmpty()) {
                    Toast.makeText(QuizActivity.this, "Kérlek válassz a lehetőségek közül!", Toast.LENGTH_SHORT).show();
                }
                else{
                    changeNextQuestion();
                }
            }
        });

    }

    private void changeNextQuestion() {
        currentQuestionPosition++;

        if ((currentQuestionPosition + 1 ) == questionLists.size()) {
            submit.setText("Kérdősor küldése!");
        }

        if (currentQuestionPosition < questionLists.size()) {
            selectedOption = "";

            option1.setBackgroundResource(R.drawable.round_back_white);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.round_back_white);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.round_back_white);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            option4.setBackgroundResource(R.drawable.round_back_white);
            option4.setTextColor(Color.parseColor("#1F6BB8"));

            questions.setText((currentQuestionPosition+1)+"/"+questionLists.size());
            question.setText(questionLists.get(currentQuestionPosition).getQuestion());
            option1.setText(questionLists.get(currentQuestionPosition).getOption1());
            option2.setText(questionLists.get(currentQuestionPosition).getOption2());
            option3.setText(questionLists.get(currentQuestionPosition).getOption3());
            option4.setText(questionLists.get(currentQuestionPosition).getOption4());
        }

        else{
            quizTimer.purge();
            quizTimer.cancel();
            Intent intent = new Intent(QuizActivity.this,ResultsActivity.class);
            intent.putExtra("topic",getTopic);
            intent.putExtra("total",questionLists.size());
            intent.putExtra("correct",getCorrectAnsw());
            startActivity(intent);

            finish();
        }
    }

    private void startTimer(TextView timertv)
    {
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (seconds == 0) {
                    totalTimeInMisn--;
                    seconds = 59;
                }
                else if(totalTimeInMisn < 0) {
                    quizTimer.purge();
                    quizTimer.cancel();

                    Intent intent = new Intent(QuizActivity.this,ResultsActivity.class);
                    intent.putExtra("correct", getCorrectAnsw());
                    intent.putExtra("topic",getTopic);
                    startActivity(intent);

                    finish();
                }
                else{
                    seconds--;

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String mins = String.valueOf(totalTimeInMisn);
                        String secs = String.valueOf(seconds);
                        if (mins.length() == 1) {
                            mins = "0" + mins;
                        }

                        if (secs.length() == 1) {
                            secs = "0"+secs;
                        }
                        timertv.setText(mins+":"+secs);

                    }
                });
            }
        },1000,1000);

    }


    private int getCorrectAnsw() {
        int correctAnswers = 0;
        try {

            for (int i = 0; i < questionLists.size(); i++) {
                String getUserSelectedAnsw = questionLists.get(i).getUserSelectedAnswer();
                final String getAnsw = questionLists.get(i).getAnswer();

                if (getUserSelectedAnsw.equals(getAnsw)) {
                    correctAnswers++;
                }
            }
            return correctAnswers;
        }
        catch (Exception e){
            return correctAnswers;
        }
    }

    private void revealAnsw() {
        String getAnsw = questionLists.get(currentQuestionPosition).getAnswer();

        if (option1.getText().toString().equals(getAnsw)) {
            option1.setBackgroundResource(R.drawable.round_back_green);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(getAnsw)) {
            option2.setBackgroundResource(R.drawable.round_back_green);
            option2.setTextColor(Color.WHITE);
        }
        else if(option3.getText().toString().equals(getAnsw)) {
            option3.setBackgroundResource(R.drawable.round_back_green);
            option3.setTextColor(Color.WHITE);
        }
        else if(option4.getText().toString().equals(getAnsw)) {
            option4.setBackgroundResource(R.drawable.round_back_green);
            option4.setTextColor(Color.WHITE);
        }
    }
}