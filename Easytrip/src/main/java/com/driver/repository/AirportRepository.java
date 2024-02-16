package com.driver.repository;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AirportRepository {
    Map<String, Airport> airportMap;
    Map<Integer, Flight> flightMap;
    Map<Integer, Passenger> passengerMap;
    Map<Integer, List<Integer>> flightPassangersMap;
    Map<Integer, List<Integer>> passangerFlightsMap;

    public AirportRepository() {
        this.airportMap = new HashMap<>();
        this.flightMap = new HashMap<>();
        this.passengerMap = new HashMap<>();
        this.flightPassangersMap = new HashMap<>();
        this.passangerFlightsMap = new HashMap<>();
    }

    public Map<String, Airport> getAirportMap() {
        return airportMap;
    }

    public Map<Integer, Flight> getFlightMap() {
        return flightMap;
    }

    public Map<Integer, Passenger> getPassengerMap() {
        return passengerMap;
    }

    public Map<Integer, List<Integer>> getFlightPassangersMap() {
        return flightPassangersMap;
    }

    public Map<Integer, List<Integer>> getPassangerFlightsMap() {
        return passangerFlightsMap;
    }

    public String addAirport(Airport airport) {
        getAirportMap().put(airport.getAirportName(),airport);
        return "SUCCESS";
    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        getFlightPassangersMap().get(flightId).add(passengerId);
        getPassangerFlightsMap().get(passengerId).add(flightId);
        return "SUCCESS";
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        getPassangerFlightsMap().get(passengerId).remove(passengerId);
        getFlightPassangersMap().get(flightId).remove(flightId);
        return "SUCCESS";
    }

    public String addFlight(Flight flight) {
        if(!getFlightMap().containsKey(flight.getFlightId())){
            getFlightMap().put(flight.getFlightId(),flight);
            getFlightPassangersMap().put(flight.getFlightId(),new ArrayList<>());
            return "SUCCESS";
        }else{
            return null;
        }
    }

    public String addPassenger(Passenger passenger) {
        //check if id exists or not
        if(!getPassengerMap().containsKey(passenger.getPassengerId())){
            //adding in passenger DB and passengerBooking DB
            getPassengerMap().put(passenger.getPassengerId(),passenger);
            getPassangerFlightsMap().put(passenger.getPassengerId(),new ArrayList<>());
            return "SUCCESS";
        }else{
            return null;
        }
    }
}