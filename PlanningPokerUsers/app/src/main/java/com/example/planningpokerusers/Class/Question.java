package com.example.planningpokerusers.Class;
//class for question
public class Question {
    private  String questions;
    private String groupId;

    public Question(String questions,String groupId) {
        this.questions = questions;
        this.groupId=groupId;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public Question(){

    }
}
