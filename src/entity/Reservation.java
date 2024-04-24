package entity;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private String customer_name;
    private Otel otel;
    private LocalDate strt_date;
    private LocalDate fnsh_date;
    private int adult_number;
    private int child_number;
    private int total_price;

    public Reservation(int id, String custmer_name, Otel otel, LocalDate strt_date, LocalDate fnsh_date, int adult_number, int child_number, int total_price) {
        this.id = id;
        this.customer_name = custmer_name;
        this.otel = otel;
        this.strt_date = strt_date;
        this.fnsh_date = fnsh_date;
        this.adult_number = adult_number;
        this.child_number = child_number;
        this.total_price = total_price;
    }
    public Reservation(String custmer_name, Otel otel, LocalDate strt_date, LocalDate fnsh_date, int adult_number, int child_number, int total_price) {
        this.customer_name = custmer_name;
        this.otel = otel;
        this.strt_date = strt_date;
        this.fnsh_date = fnsh_date;
        this.adult_number = adult_number;
        this.child_number = child_number;
        this.total_price = total_price;
    }

    public Reservation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Otel getOtel() {
        return otel;
    }

    public void setOtel(Otel otel) {
        this.otel = otel;
    }

    public LocalDate getStrt_date() {
        return strt_date;
    }

    public void setStrt_date(LocalDate strt_date) {
        this.strt_date = strt_date;
    }

    public LocalDate getFnsh_date() {
        return fnsh_date;
    }

    public void setFnsh_date(LocalDate fnsh_date) {
        this.fnsh_date = fnsh_date;
    }

    public int getAdult_number() {
        return adult_number;
    }

    public void setAdult_number(int adult_number) {
        this.adult_number = adult_number;
    }

    public int getChild_number() {
        return child_number;
    }

    public void setChild_number(int child_number) {
        this.child_number = child_number;
    }

    public int getTotal_price() {
        total_price = total_price + (otel.getRoomtype().getPrice() * adult_number) + ((otel.getRoomtype().getPrice() * child_number)/2);
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
