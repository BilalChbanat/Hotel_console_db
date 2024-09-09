package interfaces;

import classes.Hotel;

import java.util.HashMap;

public interface ReservationRepositoryInterface {

    Hotel findById(int id);
    void create(Hotel hotel);
    HashMap<Integer, Hotel> findAll();
    void update(Hotel hotel);
    void delete(Hotel hotel);
}
