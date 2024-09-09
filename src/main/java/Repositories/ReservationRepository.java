package Repositories;

import java.time.LocalDate;

public class ReservationRepository {

    private int id;
    private String client;
    private LocalDate check_in_date;
    private LocalDate check_out_date;

    public ReservationRepository(int id, String client, LocalDate check_in_date, LocalDate check_out_date) {
        this.id = id;
        this.client = client;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
