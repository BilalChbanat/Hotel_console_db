package interfaces;

import classes.Hotel;
import classes.Room;

import java.util.List;

public interface RoomRepositoryInterface {
    void create(Room room,Hotel hotel);
    Room findById(int id);
    List<Room> findAll(Hotel hotel);
    void update(Room room);
    void delete(Room room);
}
