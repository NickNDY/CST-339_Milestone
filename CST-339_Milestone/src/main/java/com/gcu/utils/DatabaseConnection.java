package com.gcu.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/springBootApplication";
            String username = "root";
            String password = "16789";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(" connected ");
        } catch (SQLException e) {
            System.out.println(String.format("Error creating database connection: %s", e.getMessage()));
        }
    }
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}