package com.example.instant_message.service;

import com.example.instant_message.db.ConnectDB;
import com.example.instant_message.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public User checkUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        // System.out.println(sql);
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()) {
            // System.out.println("Email: " + res.getString("email") + " Password: " + res.getString("password"));
            if(!email.equals(res.getString("email"))) {
                return null;
            } else {
                if (!password.equals(res.getString("password")) ) {
                    return null;
                } else {
                    User user = new User();
                    user.setId(res.getLong("id"));
                    user.setEmail(email);
                    user.setName(res.getString("name"));
                    user.setPhoneNumber(res.getString("phone_number"));
                    user.setDob(res.getDate("dob"));

                    return user;
                }
            }
        }
        return null;
    }

    public void updateUser(Long id, String email, String name, String phoneNumber, String dob) throws SQLException {
        String sql = "UPDATE users " +
                "SET email = '" + email + "', " +
                "name = '" + name + "', " +
                "phone_number = '" + phoneNumber + "', " +
                "dob = '" + dob + "' " +
                " WHERE id = " + id + "";
        System.out.println(sql);
        Statement stm = ConnectDB.getConnection().createStatement();
        stm.executeUpdate(sql);
    }

    public List<User> friendList(Long id) throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM friends where id_user1 = " + id + "";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            userList.add(findUserById(res.getLong("id_user2")));
        }
        return  userList.size()>0?userList:null;
    }

    public User findUserById(Long id) throws SQLException {
        User user = new User();
        String sql = "SELECT * FROM users where id = " + id + "";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            user.setId(res.getLong("id"));
            user.setName(res.getString("name"));
            user.setEmail(res.getString("email"));
            user.setPhoneNumber(res.getString("phone_number"));
            user.setDob(res.getDate("dob"));
        }
        return user;
    }

    public List<User> friendSuggestionList(Long id) throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE id NOT IN (SELECT id FROM users WHERE id = " + id + ")" +
                "AND id NOT IN (SELECT id_user2 FROM friend_requests WHERE id_user1 = " + id + ")" +
                "AND id NOT IN (SELECT id_user1 FROM friend_requests WHERE id_user2 = " + id + ")" +
                "AND id NOT IN (SELECT id_user2 FROM friends WHERE id_user1 = "+ id +")";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            userList.add(findUserById(res.getLong("id")));
        }
        return  userList;
    }

    public List<User> friendRequestList(Long id) throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM friend_requests WHERE id_user2 = '" + id + "';";
        Statement stm = ConnectDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while(res.next()) {
            userList.add(findUserById(res.getLong("id_user1")));
        }
        return  userList;
    }
}
