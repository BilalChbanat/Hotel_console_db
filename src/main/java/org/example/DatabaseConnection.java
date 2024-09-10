package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    // Hardcoded connection parameters
    private static final String URL = "jdbc:postgresql://localhost:5432/HotelReservation";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    // Private constructor for Singleton pattern
    private DatabaseConnection() {
        try {
            // Load the PostgreSQL driver
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error during database connection initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get the Singleton instance
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Get the connection object
    public Connection getConnection() {
        return connection;
    }

    // Close the connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
