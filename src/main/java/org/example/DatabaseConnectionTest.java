package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/HotelReservation";
        String user = "admin";
        String password = "admin";

        try {
            // Explicitly load the JDBC driver
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected to the Hotel Reservation database!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }
    }
}