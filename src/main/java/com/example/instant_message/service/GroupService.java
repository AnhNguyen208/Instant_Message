package com.example.instant_message.service;

import com.example.instant_message.db.ConnectDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GroupService {
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
}
