package com.driver.model;



public class Airport {

    private String airportName; //This is the unique key

    private int noOfTerminals;

    private City city;  //GIVEN : There will be only 1 airport in 1 city

    public Airport() {
    }

    public Airport(String airportName, int noOfTerminals, City city) {
        this.airportName = airportName;
        this.noOfTerminals = noOfTerminals;
        this.city = city;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public int getNoOfTerminals() {
        return noOfTerminals;
    }

    public void setNoOfTerminals(int noOfTerminals) {
        this.noOfTerminals = noOfTerminals;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
