package Repositories;

import classes.Hotel;
import classes.Room;
import interfaces.RoomRepositoryInterface;
import org.example.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class RoomRepository implements RoomRepositoryInterface {
    private Connection connection;

    public RoomRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    @Override
    public void create(Room room, Hotel hotel) {

    }

    @Override
    public Room findById(int id) {
        return null;
    }

    @Override
    public List<Room> findAll(Hotel hotel) {
        return List.of();
    }

    @Override
    public void update(Room room) {

    }

    @Override
    public void delete(Room room) {

    }
}
