package com.example.instant_message.entity;

import java.util.List;

public class Group {
    private Long idGroup;
    private String groupName;
    private List<User> memberList;

    public Group() {}
    public Group(Long id,String groupName, List<User> memberList) {
        this.idGroup = id;
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

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }
}
