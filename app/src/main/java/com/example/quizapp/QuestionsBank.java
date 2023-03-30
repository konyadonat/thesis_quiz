package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {
    //TODO MORE TOPICS

    private static List<QuestionList>  konyha() {
        List<QuestionList> questionLists = new ArrayList<>();

        QuestionList question1 = new QuestionList("Főzőlap","desk","cutting board","hot plate","oven","cutting board","");
        QuestionList question2 = new QuestionList("Konyha kés","Kitchen knife","Cuttion board","Fridgerator","valami","Kitchen knife","");
        questionLists.add(question1);
        questionLists.add(question2);
        return questionLists;
    }


    public static List<QuestionList> getQuestions(String topic) {
        //TODO MORE TOPICS
        switch (topic) {
            case "konyha":
                return konyha();
            default: return konyha();
        }
    }
}
