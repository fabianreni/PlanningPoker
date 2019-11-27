package com.example.planningpokeradmin;

public class GroupClass {
    private  String groupName;
    private  String groupStatus;
    public GroupClass(){

    }
    public GroupClass(String name) {
        this.groupName = name;
    }
    public GroupClass(String name,String status) {
        this.groupName = name;
        this.groupStatus=status;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String name) {
        this.groupName = name;
    }
}
