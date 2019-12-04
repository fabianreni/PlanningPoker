package com.example.planningpokerusers.Class;

public class ResultsClass {
   private String UserName;
    private String Vote;

    public ResultsClass(){

    }
    public ResultsClass(String UserName, String Vote) {
        this.UserName = UserName;
        this.Vote = Vote;
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

    public void setVote(String Vote) {
        this.Vote = Vote;
    }
}
