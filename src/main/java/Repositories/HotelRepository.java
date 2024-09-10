package Repositories;

import classes.Hotel;
import interfaces.HotelRepositoryInterface;
import org.example.DatabaseConnection;

import java.sql.Connection;
import java.util.HashMap;

public class HotelRepository implements HotelRepositoryInterface {

    private Connection connection;

    public HotelRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Hotel findById(int id) {
        return null;
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
