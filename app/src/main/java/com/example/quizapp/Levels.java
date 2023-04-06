package com.example.quizapp;

public class Levels {

    String completedlvl1;
    String completedlvl2;
    String completedlvl3;
    String completedlvl4;


    String lvl1bestattempt;
    String lvl2bestattempt;
    String lvl3bestattempt;
    String lvl4bestattempt;

    String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    Levels() {
        this.completedlvl1 = "false";
        this.completedlvl2 = "false";
        this.completedlvl3 = "false";
        this.completedlvl4 = "false";

        this.lvl1bestattempt = "0";
        this.lvl2bestattempt = "0";
        this.lvl3bestattempt = "0";
        this.lvl4bestattempt = "0";
    }

    public void setCompletedlvl1(String completedlvl1) {
        this.completedlvl1 = completedlvl1;
    }

    public void setCompletedlvl2(String completedlvl2) {
        this.completedlvl2 = completedlvl2;
    }

    public void setCompletedlvl3(String completedlvl3) {
        this.completedlvl3 = completedlvl3;
    }

    public void setCompletedlvl4(String completedlvl4) {
        this.completedlvl4 = completedlvl4;
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

    public String getCompletedlvl1() {
        return completedlvl1;
    }

    public String getCompletedlvl2() {
        return completedlvl2;
    }

    public String getCompletedlvl3() {
        return completedlvl3;
    }

    public String getCompletedlvl4() {
        return completedlvl4;
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
}
