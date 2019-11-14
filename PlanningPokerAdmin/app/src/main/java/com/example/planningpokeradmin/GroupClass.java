package com.example.planningpokeradmin;

public class GroupClass {
    private  String groupName;
    public GroupClass(){

    }
    public GroupClass(String questions) {
        this.groupName = questions;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String questions) {
        this.groupName = questions;
    }
}
