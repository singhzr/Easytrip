package com.driver.model;

import java.util.Date;

public class Flight {

    private int flightId; //This is a unique key for a flight

    private City fromCity;

    private City toCity;

    private int maxCapacity;

    private Date flightDate;

    private double duration;

    public Flight() {

    }

    public Flight(int flightId, City fromCity, City toCity, int maxCapacity, Date flightDate, double duration) {
        this.flightId = flightId;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.maxCapacity = maxCapacity;
        this.flightDate = flightDate;
        this.duration = duration;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

}
