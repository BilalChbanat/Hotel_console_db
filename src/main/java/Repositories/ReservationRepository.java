package Repositories;


import classes.Hotel;
import interfaces.ReservationRepositoryInterface;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.HashMap;

public class ReservationRepository implements ReservationRepositoryInterface {

    private Connection connection;

    public ReservationRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }


    @Override
    public Hotel findById(int id) {
        Hotel hotel = null;
        String query = "SELECT * FROM hotels WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hotel = new Hotel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("location"),
                        resultSet.getInt("rating")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding hotel: " + e.getMessage());
        }
        return hotel;
    }

    @Override
    public void create(Hotel hotel) {

    }

    @Override
    public HashMap<Integer, Hotel> findAll() {
        return null;
    }

    @Override
    public void update(Hotel hotel) {

    }

    @Override
    public void delete(Hotel hotel) {

    }
}
