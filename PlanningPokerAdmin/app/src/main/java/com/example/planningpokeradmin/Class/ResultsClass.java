package com.example.planningpokeradmin.Class;

public class ResultsClass {
    private String UserName;
    private String Vote;

    public ResultsClass(){

    }
    public ResultsClass(String UserName, String vote) {
        this.UserName = UserName;
        this.Vote = vote;
    }

    public String getName() {
        return UserName;
    }

    public void setName(String UserName) {
        this.UserName = UserName;
    }

    public String getVote() {
        return Vote;
    }

    public void setVote(String vote) {
        this.Vote = vote;
    }
}
