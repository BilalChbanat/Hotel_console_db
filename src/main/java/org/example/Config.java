package org.example;


import java.sql.SQLException;

public class Config {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/HotelReservation";
        String user = "admin";
        String password = "admin";

        try {
            java.sql.Connection connection = Connection.getInstance().getConnection(url, user, password);
//            System.out.println("Successfully connected to the Hotel Reservation database!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
        }
    }
}

