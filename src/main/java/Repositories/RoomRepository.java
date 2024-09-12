package Repositories;

import classes.Hotel;
import classes.Room;
import interfaces.RoomRepositoryInterface;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements RoomRepositoryInterface {
    private Connection connection;

    public RoomRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void create(Room room) {
        try {
            String query = "INSERT INTO rooms (hotel_id, isAvailable, capacity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, room.getHotel().getId());
            stmt.setBoolean(2, room.isAvailable());
            stmt.setInt(3, room.getCapacity());
            stmt.setDouble(4, room.getPrice());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating room failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    room.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating room failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Room findById(int id) {
        try {
            String query = "SELECT * FROM rooms WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Hotel hotel = new HotelRepository().findById(rs.getInt("hotel_id"));
                Room room = new Room(
                        rs.getDouble("price"),
                        rs.getInt("capacity"),
                        rs.getBoolean("isAvailable"),
                        hotel
                );
                room.setId(rs.getInt("id"));
                return room;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> findByHotel(Hotel hotel) {
        List<Room> rooms = new ArrayList<>();
        try {
            String query = "SELECT * FROM rooms WHERE hotel_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, hotel.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Room room = new Room(
                        rs.getDouble("price"),
                        rs.getInt("capacity"),
                        rs.getBoolean("isAvailable"),
                        hotel
                );
                room.setId(rs.getInt("id"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        try {
            String query = "SELECT * FROM rooms";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Hotel hotel = new HotelRepository().findById(rs.getInt("hotel_id"));
                Room room = new Room(
                        rs.getDouble("price"),
                        rs.getInt("capacity"),
                        rs.getBoolean("isAvailable"),
                        hotel
                );
                room.setId(rs.getInt("id"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }


    @Override
    public void update(Room room) {
        try {
            String query = "UPDATE rooms SET hotel_id = ?, isAvailable = ?, capacity = ?, price = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, room.getHotel().getId());
            stmt.setBoolean(2, room.isAvailable());
            stmt.setInt(3, room.getCapacity());
            stmt.setDouble(4, room.getPrice());
            stmt.setInt(5, room.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Room room) {
        try {
            String query = "DELETE FROM rooms WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, room.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
