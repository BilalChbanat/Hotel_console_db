package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static Connection instance;
    private java.sql.Connection connection;

    private Connection() {
        try {
            // Explicitly load the JDBC driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }
    }

    public static synchronized Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public java.sql.Connection getConnection(String url, String user, String password) {
        if (connection == null || isConnectionClosed()) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Connection failed. Check output console.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    private boolean isConnectionClosed() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }
}