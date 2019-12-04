package com.example.planningpokeradmin.Class;

public class UserClass {
    private  String userName;
    private  String userVote;
    public UserClass(){

    }
    public UserClass(String name) {
        this.userName = name;
    }
    public UserClass(String name,String userVote) {
        this.userName = name;
        this.userVote=userVote;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserVote() {
        return userVote;
    }

    public void setUserVote(String userVote) {
        this.userVote = userVote;
    }
}
