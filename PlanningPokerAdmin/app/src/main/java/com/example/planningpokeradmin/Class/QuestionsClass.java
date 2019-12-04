package com.example.planningpokeradmin.Class;

public class QuestionsClass {
    private  String questions;
    private String groupId, status;


    public QuestionsClass(String questions,String groupId, String status) {
        this.questions = questions;
        this.groupId=groupId;
        this.status = status;
    }

    public String getStatus() {
        return status;
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
