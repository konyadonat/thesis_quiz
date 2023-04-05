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

    //TODO fields that validate percentage
    //TODO e.g level1passed=false; , ha megvan a level 1 80% akkor true és mehet a 2.szintre
    private String email;

    private String password;

    private String completedlvl1;
    private String completedlvl2;
    private String completedlvl3;

    public String getCompletedlvl1() {
        return completedlvl1;
    }

    public String getCompletedlvl2() {
        return completedlvl2;
    }

    public String getCompletedlvl3() {
        return completedlvl3;
    }

    public String getLvl1bestattempt() {
        return lvl1bestattempt;
    }

    public String getLvl2bestattempt() {
        return lvl2bestattempt;
    }

    public String getLvl3bestattempt() {
        return lvl3bestattempt;
    }

    public String getLvl4bestattempt() {
        return lvl4bestattempt;
    }

    private String lvl1bestattempt;
    private String lvl2bestattempt;
    private String lvl3bestattempt;
    private String lvl4bestattempt;

    public void setCompletedlvl1(String completedlvl1) {
        this.completedlvl1 = completedlvl1;
    }

    public void setCompletedlvl2(String completedlvl2) {
        this.completedlvl2 = completedlvl2;
    }

    public void setCompletedlvl3(String completedlvl3) {
        this.completedlvl3 = completedlvl3;
    }

    public void setLvl1bestattempt(String lvl1bestattempt) {
        this.lvl1bestattempt = lvl1bestattempt;
    }

    public void setLvl2bestattempt(String lvl2bestattempt) {
        this.lvl2bestattempt = lvl2bestattempt;
    }

    public void setLvl3bestattempt(String lvl3bestattempt) {
        this.lvl3bestattempt = lvl3bestattempt;
    }

    public void setLvl4bestattempt(String lvl4bestattempt) {
        this.lvl4bestattempt = lvl4bestattempt;
    }

    private String username;

    public String getUsername() {
        int index = email.indexOf('@');
        username = email.substring(0,index);
        return username;
    }
    private String score;

    public User(String email) {
        this.email = email;
        this.completedlvl1 = "false";
        this.completedlvl2 = "false";
        this.completedlvl3 = "false";
        this.lvl1bestattempt = "0";
        this.lvl2bestattempt = "0";
        this.lvl3bestattempt = "0";
        this.lvl4bestattempt = "0";
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.score = "0";
    }
    public User() {
        this.score = "0";
        this.completedlvl1 = "false";
        this.completedlvl2 = "false";
        this.completedlvl3 = "false";
        this.lvl1bestattempt = "0";
        this.lvl2bestattempt = "0";
        this.lvl3bestattempt = "0";
        this.lvl4bestattempt = "0";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailEmptyException, EmailNullException, EmailFormatException {
        if (email.length() == 0)
            throw new EmailEmptyException("Az email nem lehet üres!");

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

    public void setScore(String score) {
        this.score = score;
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

    public String getScore() {
        return score;
    }
}