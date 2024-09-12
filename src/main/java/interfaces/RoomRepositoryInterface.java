package interfaces;

import classes.Hotel;
import classes.Room;

import java.util.List;

public interface RoomRepositoryInterface {
    void create(Room room);
    Room findById(int id);
    List<Room> findByHotel(Hotel hotel);
    List<Room> findAll();  // Option to find all rooms across all hotels
    void update(Room room);
    void delete(Room room);
}
