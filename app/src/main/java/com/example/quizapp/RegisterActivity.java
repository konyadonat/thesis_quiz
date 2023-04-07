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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button backtologin = findViewById(R.id.registerbackbutton);
        EditText emailet = findViewById(R.id.editTextTextEmailAddressregister);
        EditText passwordet = findViewById(R.id.editTextTextPasswordregister);
        EditText passwordAgainet = findViewById(R.id.editTextTextPassword2register);
        Button register = findViewById(R.id.registerbutton);
        ImageView logo = findViewById(R.id.imageView2);

        DatabaseReference userReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users");
        DatabaseReference levelReference = FirebaseDatabase.getInstance("https://steng-dab96-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Levels");


        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        logo.setImageResource(R.drawable.stenglogo);


        register.setOnClickListener(view -> {
            String email = emailet.getText().toString();
            String password = passwordet.getText().toString();
            Levels levels = new Levels();
            User user = new User();
            try {
                user.setEmail(email);
                user.setPassword(password);
                levels.setUsername(user.getUsername());
                //int index = email.indexOf('@');
                //String username = email.substring(0,index);

                if(passwordAgainet.getText().toString().equals(password)) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {

                                //TODO CHECK IF USERNAME ALREADY EXISTS
                                Query checkUsername = userReference.orderByChild("username").equalTo(user.getUsername());
                                checkUsername.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            emailet.setError("Ezzel az email címmel már regisztráltak!");
                                            emailet.setText("");
                                            emailet.requestFocus();
                                        }
                                        else{
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                                            userReference.child(user.getUsername()).setValue(user);
                                            levelReference.child(user.getUsername()).setValue(levels);
                                            Toast.makeText(RegisterActivity.this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            } else {
                                Toast.makeText(RegisterActivity.this, "Regisztrációs hiba: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    throw new PasswordNotTheSameException("A két jelszó nem egyezik!");
                }

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
            catch (PasswordNotTheSameException e) {
                passwordet.setError(e.getMessage());
                passwordet.setText("");
                passwordAgainet.setText("");
                passwordet.requestFocus();
            }
            catch (Exception e) {
                Toast.makeText(this, "Hiba! " +e.getMessage(), Toast.LENGTH_SHORT).show();
            }

    });
}
}