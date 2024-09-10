package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Config {

    public static void main(String[] args) {
        DatabaseConnection connectionManager = DatabaseConnection.getInstance();

        Connection connection = connectionManager.getConnection();

        if (connection != null) {
            System.out.println("Successfully connected to the database!");

            try (Statement statement = connection.createStatement()) {
                // Perform a test query (e.g., querying the current timestamp)
                ResultSet resultSet = statement.executeQuery("SELECT CURRENT_TIMESTAMP");

                if (resultSet.next()) {
                    System.out.println("Current Timestamp: " + resultSet.getString(1));
                }
            } catch (SQLException e) {
                System.out.println("Error executing test query: " + e.getMessage());
            } finally {
                // Close the connection
                connectionManager.closeConnection();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}


//public class Config {
//    public static void main(String[] args) {
//        String url = "jdbc:postgresql://localhost:5432/HotelReservation";
//        String user = "admin";
//        String password = "admin";
//
//        try {
//            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection(url, user, password);
////            System.out.println("Successfully connected to the Hotel Reservation database!");
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Connection failed. Check output console.");
//            e.printStackTrace();
//        }
//    }
//}

