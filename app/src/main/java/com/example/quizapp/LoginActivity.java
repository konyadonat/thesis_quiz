package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quizapp.exceptions.EmailEmptyException;
import com.example.quizapp.exceptions.EmailFormatException;
import com.example.quizapp.exceptions.EmailNullException;
import com.example.quizapp.exceptions.PasswordEmptyException;
import com.example.quizapp.exceptions.PasswordIsDigitsOnlyException;
import com.example.quizapp.exceptions.PasswordNullException;
import com.example.quizapp.exceptions.PasswordTooShortException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    static FirebaseAuth mAuth;

    static FirebaseUser currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailet = findViewById(R.id.editTextTextEmailAddress);
        EditText passwordet = findViewById(R.id.editTextTextPassword);
        Button login = findViewById(R.id.button);


        Button test = findViewById(R.id.testbutton);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        ImageView logo = findViewById(R.id.imageView2);
        logo.setImageResource(R.drawable.stenglogo);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailet.getText().toString();
                String password = passwordet.getText().toString();

                User user = new User();

                try {
                    user.setEmail(email);

                    if (password == null) {
                        throw new PasswordNullException("Írd be a jelszavad!");
                    }

                    if (password == "") {
                        throw new PasswordEmptyException("Írd be a jelszavad!");
                    }
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Sikeres bejelentkezés!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                                startActivity(intent);
                                finish();

                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Helytelen felhasználói adatok!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                } catch (EmailEmptyException e) {
                    emailet.setError(e.getMessage());
                    emailet.requestFocus();
                } catch (EmailNullException e) {
                    emailet.setError(e.getMessage());
                    emailet.requestFocus();
                } catch (EmailFormatException e) {
                    emailet.setError(e.getMessage());
                    emailet.requestFocus();
                } catch (PasswordNullException e) {
                    passwordet.setError(e.getMessage());
                    passwordet.requestFocus();
                } catch (PasswordEmptyException e) {
                    passwordet.setError(e.getMessage());
                    passwordet.requestFocus();
                }
                catch (IllegalArgumentException e) {
                    passwordet.setError("Írd be a jelszavad!");
                    passwordet.requestFocus();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        currentuser = mAuth.getCurrentUser();
        if (currentuser != null){
            Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }
}