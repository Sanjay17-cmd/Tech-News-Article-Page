package com.technews.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/technewsdb?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "root");
        } catch (Exception e) {
            System.err.println("DB Connection Error: " + e.getMessage());
        }
        return conn;
    }
}
