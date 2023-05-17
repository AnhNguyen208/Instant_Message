package com.example.instant_message.service;

import com.example.instant_message.db.ConnectDB;
import com.example.instant_message.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
