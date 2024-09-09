package classes;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private Room room;
    private String client;
    private LocalDate check_in_date;
    private LocalDate check_out_date;

    public Reservation(int id, Room room, String client, LocalDate check_in_date, LocalDate check_out_date) {
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
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
}
