package Repositories;

public class HomeRepository {

    private int roomNumber;
    private int capacity;
    private boolean availability;

    public HomeRepository(int roomNumber, int capacity, boolean availability) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.availability = availability;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

}
