package classes;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private Room room;
    private Customer client;
    private LocalDate check_in_date;
    private LocalDate check_out_date;

    public Reservation(int id, Room room, Customer client, LocalDate check_in_date, LocalDate check_out_date) {
        this.id = id;
        this.room = room;
        this.client = client;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    @Override
    public String toString() {
        return "\nReservation" +
                "id:" + id +
                ", room:" + room +
                ", client:'" + client + '\'' +
                ", Check In:" + check_in_date +
                ", Check Out:" + check_out_date;
    }
}
