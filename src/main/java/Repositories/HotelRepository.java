package Repositories;

import classes.Hotel;
import interfaces.HotelRepositoryInterface;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.HashMap;

public class HotelRepository implements HotelRepositoryInterface {

    private final Connection connection;

    public HotelRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Hotel findById(int id) {
        String query = "SELECT * FROM hotels WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Hotel hotel = new Hotel(
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone")
                    );
                    hotel.setId(rs.getInt("id"));
                    return hotel;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // You could replace this with proper logging.
        }
        return null;
    }

    @Override
    public void create(Hotel hotel) {
        String query = "INSERT INTO hotels (name, address, phone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getAddress());
            stmt.setString(3, hotel.getPhone());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating hotel failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    hotel.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating hotel failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging in production.
        }
    }

    @Override
    public HashMap<Integer, Hotel> findAll() {
        HashMap<Integer, Hotel> hotels = new HashMap<>();
        String query = "SELECT * FROM hotels";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Hotel hotel = new Hotel(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
                hotel.setId(rs.getInt("id"));
                hotels.put(hotel.getId(), hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging in production.
        }
        return hotels;
    }

    @Override
    public void update(Hotel hotel) {
        String query = "UPDATE hotels SET name = ?, address = ?, phone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getAddress());
            stmt.setString(3, hotel.getPhone());
            stmt.setInt(4, hotel.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging in production.
        }
    }

    @Override
    public void delete(Hotel hotel) {
        String query = "DELETE FROM hotels WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, hotel.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging in production.
        }
    }
}
