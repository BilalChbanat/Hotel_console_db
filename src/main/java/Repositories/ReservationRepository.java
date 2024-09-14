package Repositories;

import classes.Reservation;
import classes.Room;
import classes.Customer;
import interfaces.ReservationRepositoryInterface;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservationRepository implements ReservationRepositoryInterface {

    private Connection connection;

    public ReservationRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Reservation findById(int id) {
        try {
            String query = "SELECT * FROM reservations WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Reservation(
                        rs.getInt("id"),
                        new RoomRepository().findById(rs.getInt("room_id")),
                        new CustomerRepository().findById(rs.getInt("user_id")),
                        rs.getDate("check_in").toLocalDate(),
                        rs.getDate("check_out").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Reservation reservation) {
        try {
            String query = "INSERT INTO reservations (room_id, user_id, check_in, check_out) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, reservation.getRoom().getId());
            stmt.setInt(2, reservation.getClient().getId());
            stmt.setDate(3, Date.valueOf(reservation.getCheck_in_date()));
            stmt.setDate(4, Date.valueOf(reservation.getCheck_out_date()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Reservation> findAll() {
        HashMap<Integer, Reservation> reservations = new HashMap<>();
        try {
            String query = "SELECT * FROM reservations";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("id"),
                        new RoomRepository().findById(rs.getInt("room_id")),
                        new CustomerRepository().findById(rs.getInt("user_id")),
                        rs.getDate("check_in").toLocalDate(),
                        rs.getDate("check_out").toLocalDate()
                );
                reservations.put(reservation.getId(), reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void update(Reservation reservation) {
        try {
            String query = "UPDATE reservations SET room_id = ?, user_id = ?, check_in = ?, check_out = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, reservation.getRoom().getId());
            stmt.setInt(2, reservation.getClient().getId());
            stmt.setDate(3, Date.valueOf(reservation.getCheck_in_date()));
            stmt.setDate(4, Date.valueOf(reservation.getCheck_out_date()));
            stmt.setInt(5, reservation.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Reservation reservation) {
        try {
            String query = "DELETE FROM reservations WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, reservation.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds all reservations for a specific room.
     *
     * @param roomId The ID of the room to check for reservations.
     * @return A list of reservations for the given room.
     */
    public List<Reservation> findByRoom(int roomId) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            String query = "SELECT * FROM reservations WHERE room_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("id"),
                        new RoomRepository().findById(rs.getInt("room_id")),
                        new CustomerRepository().findById(rs.getInt("user_id")),
                        rs.getDate("check_in").toLocalDate(),
                        rs.getDate("check_out").toLocalDate()
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
