package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.exceptions.EmailEmptyException;
import com.example.quizapp.exceptions.EmailFormatException;
import com.example.quizapp.exceptions.EmailNullException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    String email ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("hu");


        TextView emailtv = findViewById(R.id.editTextForgotPassword);
        Button resetpw = findViewById(R.id.forgotpasswordbutton);

        resetpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    email = emailtv.getText().toString();
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        throw new EmailFormatException("Az email formátuma nem megfelő!");
                    }
                    if (email.length() == 0) {
                        throw new EmailEmptyException("Az email nem lehet üres!");
                    }
                    if (TextUtils.isEmpty(email)){
                        throw new EmailEmptyException("Az email nem lehet üres!");
                    }
                    if (TextUtils.equals(email,null)){
                        throw new EmailNullException("Az email értéke nem lehet null!");
                    }
                    
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ForgotPasswordActivity.this, "Elküldtük az emailt!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(ForgotPasswordActivity.this, "Ez az email nem létezik!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    
                    
                }
                catch (EmailFormatException e) {
                    emailtv.setError(e.getMessage().toString());
                    emailtv.requestFocus();
                } catch (EmailEmptyException e) {
                    emailtv.setError(e.getMessage().toString());
                    emailtv.requestFocus();
                } catch (EmailNullException e) {
                    emailtv.setError(e.getMessage().toString());
                    emailtv.requestFocus();
                }
                catch (Exception e) {
                    Toast.makeText(ForgotPasswordActivity.this, "Hiba "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}