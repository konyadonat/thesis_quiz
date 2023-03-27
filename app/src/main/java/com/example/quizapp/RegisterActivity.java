package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.example.quizapp.exceptions.PasswordNotTheSameException;
import com.example.quizapp.exceptions.PasswordNullException;
import com.example.quizapp.exceptions.PasswordTooShortException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.BreakIterator;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText emailet = findViewById(R.id.editTextTextEmailAddressregister);
        EditText passwordet = findViewById(R.id.editTextTextPasswordregister);
        EditText passwordAgainet = findViewById(R.id.editTextTextPassword2register);
        Button register = findViewById(R.id.registerbutton);
        ImageView logo = findViewById(R.id.imageView2);

        mAuth = FirebaseAuth.getInstance();

        logo.setImageResource(R.drawable.bg);


        register.setOnClickListener(view -> {
            String email = emailet.getText().toString();
            String password = passwordet.getText().toString();

            User user = new User();
            try {
                user.setEmail(email);
                user.setPassword(password);

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Regisztrációs hiba: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
            } catch (PasswordTooShortException e) {
                passwordet.setError(e.getMessage());
                passwordet.requestFocus();
            } catch (PasswordNullException e) {
                passwordet.setError(e.getMessage());
                passwordet.requestFocus();
            } catch (PasswordIsDigitsOnlyException e) {
                passwordet.setError(e.getMessage());
                passwordet.requestFocus();
            } catch (PasswordEmptyException e) {
                passwordet.setError(e.getMessage());
                passwordet.requestFocus();
            }
            catch (Exception e) {
                Toast.makeText(this, "Hiba! " +e.getMessage(), Toast.LENGTH_SHORT).show();
            }

    });
}
}