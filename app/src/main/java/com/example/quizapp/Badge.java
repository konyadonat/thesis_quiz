package com.example.quizapp;

public class Badge {

    private String completedlevel1;
    private String completedlevel2;
    private String completedlevel3;
    private String completedlevel4;

    private String score50;
    private String score100;


    private String username;

    public String getCompletedlevel2() {
        return completedlevel2;
    }

    public void setCompletedlevel2(String completedlevel2) {
        this.completedlevel2 = completedlevel2;
    }

    public String getCompletedlevel3() {
        return completedlevel3;
    }

    public void setCompletedlevel3(String completedlevel3) {
        this.completedlevel3 = completedlevel3;
    }

    public String getCompletedlevel4() {
        return completedlevel4;
    }

    public void setCompletedlevel4(String completedlevel4) {
        this.completedlevel4 = completedlevel4;
    }

    public String getScore50() {
        return score50;
    }

    public void setScore50(String score50) {
        this.score50 = score50;
    }

    public String getScore100() {
        return score100;
    }

    public void setScore100(String score100) {
        this.score100 = score100;
    }

    public String getUsername() {
        return username;
    }

    public String getCompletedlevel1() {
        return completedlevel1;
    }

    public void setCompletedlevel1(String completedlevel1) {
        this.completedlevel1 = completedlevel1;
    }

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
