package Repositories;

import classes.Hotel;
import classes.Reservation;
import classes.Room;
import classes.Customer;
import interfaces.ReservationRepositoryInterface;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.HashMap;

public class ReservationRepository implements ReservationRepositoryInterface {


    @Override
    public Reservation findById(int id) {
        return null;
    }

    @Override
    public void create(Reservation reservation) {

    }

    @Override
    public HashMap<Integer, Reservation> findAll() {
        return null;
    }

    @Override
    public void update(Reservation Reservation) {

    }

    @Override
    public void delete(Reservation Reservation) {

    }
}