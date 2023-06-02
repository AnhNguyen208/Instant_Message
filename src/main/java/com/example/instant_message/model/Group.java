package com.example.instant_message.model;

import java.util.List;

public class Group {
    private String groupName;
    private List<User> memberList;

    public Group(String groupName, List<User> memberList) {
        this.groupName = groupName;
        this.memberList = memberList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<User> memberList) {
        this.memberList = memberList;
    }
}
