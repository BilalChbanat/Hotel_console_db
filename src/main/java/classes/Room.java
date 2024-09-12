package classes;

public class Room {

    private int id;
    private double price;
    private int capacity;
    private boolean isAvailable;
    private Hotel hotel;

    public Room( double price, int capacity,boolean isAvailable, Hotel hotel) {
        this.isAvailable = isAvailable;
        this.price = price;
        this.capacity = capacity;
        this.hotel = hotel;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "\nRoom{" +
                "id:" + id +
                ", price:" + price+" MAD"+
                ", capacity:" + capacity +
                ", isAvailable:" + isAvailable +
                ", hotel:" + hotel.getName() +
                '}';
    }
}
