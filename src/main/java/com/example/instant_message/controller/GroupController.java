package com.example.instant_message.controller;

import com.example.instant_message.db.ConnectDB;
import com.example.instant_message.entity.Group;
import com.example.instant_message.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupController extends BaseController{
    private UserController userController = new UserController();
    public void createGroup(String name, List<Long> idList) throws SQLException {
        Long idGroup = 0L;
        String sql = "INSERT INTO group_chats " +
                "(`name`, `number_of_member`) VALUES ('"+ name +"', '" + idList.size() + "')";
        Statement stm = ConnectDB.getConnection().createStatement();
        stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stm.getGeneratedKeys();
        if(rs.next()){
            idGroup = rs.getLong(1);
        }

        for (Long id : idList) {
            sql = "INSERT INTO group_members " +
                    "(`id_group`, `id_member`) VALUES ('"+ idGroup +"', '" + id + "')";
            stm = ConnectDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        }
    }

    public Group findGroupById(Long id_group) throws SQLException {
        Group group = new Group();
        String sql = "SELECT * FROM group_chats WHERE id = " + id_group + "";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            group.setGroupName(res.getString("name"));
            group.setMemberList(getMemberList(id_group));
        }

        return group;
    }

    public List<Group> getGroupList(Long id_member) throws SQLException {
        List<Group> groupList = new ArrayList<>();
        String sql = "SELECT id_group FROM group_members WHERE id_member = " + id_member + "";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            groupList.add(findGroupById(res.getLong("id_group")));
        }
        return groupList;
    }

    private List<User> getMemberList(Long idGroup) throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM group_members WHERE id_group = " + idGroup + "";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            userList.add(userController.findUserById(res.getLong("id_member")));
        }
        return userList;
    }
}
