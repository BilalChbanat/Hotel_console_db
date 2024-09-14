package interfaces;

import classes.Reservation;
import java.util.HashMap;
import java.util.List;

public interface ReservationRepositoryInterface {

    Reservation findById(int id);
    void create(Reservation reservation);
    HashMap<Integer, Reservation> findAll();
    void update(Reservation reservation);
    void delete(Reservation reservation);


    List<Reservation> findByRoom(int roomId);
}
