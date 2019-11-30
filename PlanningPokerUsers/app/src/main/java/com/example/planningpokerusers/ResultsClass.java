package com.example.planningpokerusers;

public class ResultsClass {
   private String name;
    private String vote;

    public ResultsClass(){

    }
    public ResultsClass(String name, String vote) {
        this.name = name;
        this.vote = vote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
