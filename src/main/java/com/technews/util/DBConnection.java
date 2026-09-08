package com.technews.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to MySQL
            // NOTE: If your MySQL password is empty or different, change "root" below!
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/technewsdb?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
        } catch (Exception e) {
            System.err.println("DB Connection Error: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
