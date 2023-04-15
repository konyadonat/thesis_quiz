package com.example.quizapp;

public class Badge {

    private String completedlevel1;
    private String completedlevel2;
    private String completedlevel3;
    private String completedlevel4;

    private String score50;
    private String score100;


    //TODO IF I HAVE MORE TIME
    //private String streak5;
    //private String streak10;
    //private String streak25;


    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public Badge() {
        this.completedlevel1 = "false";
        this.completedlevel2 = "false";
        this.completedlevel3 = "false";
        this.completedlevel4 = "false";

        this.score50 = "false";
        this.score100 = "false";

    }

}
