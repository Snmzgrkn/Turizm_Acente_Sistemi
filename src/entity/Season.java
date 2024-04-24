package entity;

import java.time.LocalDate;
import java.util.Date;

public class Season {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double rateMultiplier;

    public Season(int id, String name, LocalDate startDate, LocalDate endDate, double rateMultiplier) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rateMultiplier = rateMultiplier;
    }

    public Season() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getRateMultiplier() {
        return rateMultiplier;
    }

    public void setRateMultiplier(double rateMultiplier) {
        this.rateMultiplier = rateMultiplier;
    }
}
