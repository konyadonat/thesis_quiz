package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    private static List<QuestionList>  konyha() {
        List<QuestionList> questionLists = new ArrayList<>();

        QuestionList question1 = new QuestionList("Főzőlap","desk","cutting board","hot plate","oven","hot plate","");
        QuestionList question2 = new QuestionList("Konyha kés","Kitchen knife","Cuttion board","Fridgerator","valami","Kitchen knife","");
        questionLists.add(question1);
        questionLists.add(question2);
        return questionLists;
    }


    public static List<QuestionList> getQuestions(String topic) {
        switch (topic) {
            case "konyha":
                return konyha();
            default: return konyha();
        }
    }
}
