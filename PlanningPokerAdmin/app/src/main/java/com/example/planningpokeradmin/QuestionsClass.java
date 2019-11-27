package com.example.planningpokeradmin;

public class QuestionsClass {
    private  String questions;
    private String groupId;

    public QuestionsClass(String questions,String groupId) {
        this.questions = questions;
        this.groupId=groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public QuestionsClass(){

    }
}
