package com.example.quizapp;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.quizapp.exceptions.EmailEmptyException;
import com.example.quizapp.exceptions.EmailFormatException;
import com.example.quizapp.exceptions.EmailNullException;
import com.example.quizapp.exceptions.PasswordEmptyException;
import com.example.quizapp.exceptions.PasswordIsDigitsOnlyException;
import com.example.quizapp.exceptions.PasswordNullException;
import com.example.quizapp.exceptions.PasswordTooShortException;

public class User {

    private String email;

    private String password;

    private int score;

    public User(String email, String password, int score) {
        this.email = email;
        this.password = password;
        this.score = score;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailEmptyException, EmailNullException, EmailFormatException {
        if (TextUtils.isEmpty(email)){
            throw new EmailEmptyException("Az email nem lehet üres!");
        }
        if (TextUtils.equals(email,null)){
            throw new EmailNullException("Az email értéke nem lehet null!");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            throw new EmailFormatException("Az email formátuma nem megfelelő!");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordTooShortException, PasswordIsDigitsOnlyException, PasswordEmptyException, PasswordNullException {
        if (password.length() < 8){
            throw new PasswordTooShortException("A jelszó hossza legyen legalább 8 karakter!");
        }
        if (TextUtils.isDigitsOnly(password)) {
            throw new PasswordIsDigitsOnlyException("A jelszóban szerepeljen karakter is!");
        }
        if (TextUtils.isEmpty(password)) {
            throw new PasswordEmptyException("A jelszó nem lehet üres!");
        }
        if (TextUtils.equals(password,null)) {
            throw new PasswordNullException("A jelszó értéke nem lehet null!");
        }
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = 0;
    }
}