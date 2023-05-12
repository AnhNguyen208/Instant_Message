package com.example.instant_message.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    private static final String url = "jdbc:mysql://localhost/chat";
    private static final String username = "root";
    private static final String pwd = "dptk2008";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, pwd);
            System.out.println("connect to db successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
