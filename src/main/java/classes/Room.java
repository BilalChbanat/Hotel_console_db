package classes;

public class Room {

    private int room_id;
    private double price;
    private int capacity;
    private boolean isAvailable;
    private Hotel hotel;

    public Room(int room_id, double price, int capacity,boolean isAvailable, Hotel hotel) {
        this.room_id = room_id;
        this.isAvailable = isAvailable;
        this.price = price;
        this.capacity = capacity;
        this.hotel = hotel;

    }

    public int getRoom_id() {

        return room_id;
    }

    private void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
