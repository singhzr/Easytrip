package com.driver.service;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AirportService {

    public AirportRepository airportRepository = new AirportRepository();
//    @Autowired
//    AirportRepository airportRepository;

    Map<Integer, List<Integer>> flightBookings = airportRepository.getFlightPassangersMap();
    Map<Integer, List<Integer>>passengerBookings = airportRepository.getPassangerFlightsMap();
    Map<Integer,Flight> flights = airportRepository.getFlightMap();
    Map<Integer,Passenger> passengers = airportRepository.getPassengerMap();
    Map<String,Airport> airports = airportRepository.getAirportMap();
    public String addAirport(Airport airport) {

        return airportRepository.addAirport(airport);
    }

    public String addPassenger(Passenger passenger) {
        return airportRepository.addPassenger(passenger);
    }

    public String addFlight(Flight flight) {
        return airportRepository.addFlight(flight);
    }

    public String getLargestAirportName() {
        String airportName = "";
        int count = Integer.MIN_VALUE;

        for (Airport airport : airports.values()){
            if (airport.getNoOfTerminals() >= count){
                if (airportName == "" || airport.getNoOfTerminals() > count){
                    airportName = airport.getAirportName();
                    count= airport.getNoOfTerminals();
                }else if(airport.getNoOfTerminals() == count){
                    if (airportName.compareTo(airport.getAirportName())>0){
                        airportName = airport.getAirportName();
                    }
                }
            }
        }
        return airportName;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        if(flights.size()==0)return -1;
        double time=Double.MAX_VALUE;
        for(Flight flight:flights.values()){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)){
                if(flight.getDuration()<time)time=flight.getDuration();
            }
        }
        if(time==Double.MAX_VALUE)return -1;
        else return time;
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        int numberOfPeople = 0;
        if(flightBookings.isEmpty() || flights.isEmpty() || airports.isEmpty()){
            return 0;
        }
        for (Flight flight : flights.values()) {
            if (date.equals(flight.getFlightDate())) {
                if (flight.getFromCity().equals(airports.get(airportName).getCity()) || flight.getToCity().equals(airports.get(airportName).getCity())) {
                    numberOfPeople += flightBookings.get(flight.getFlightId()).size();
                }
            }
        }
        return numberOfPeople;
    }

    public int calculateFlightFare(Integer flightId) {
        if (flightBookings.containsKey(flightId)){
            return 3000+(flightBookings.get(flightId).size()*50);
        }else{
            return 0;
        }
    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        //if flight and passenger not exists
        if(!flightBookings.containsKey(flightId) || !passengerBookings.containsKey(passengerId)){
            return "FAILURE";
        }else if(flightBookings.get(flightId).size() == flights.get(flightId).getMaxCapacity()){
            //if flight max capacity reached
            return "FAILURE";
        }else if(flightBookings.get(flightId).contains(passengerId)){
            //if passenger has already booked flight
            return "FAILURE";
        }else{
            return airportRepository.bookATicket(flightId,passengerId);
        }
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        if(!flightBookings.containsKey(flightId) || !passengerBookings.containsKey(passengerId)){
            return "FAILURE";
        }else if(!flightBookings.get(flightId).contains(passengerId)){
            return "FAILURE";
        }
        else{
            return airportRepository.cancelATicket(flightId,passengerId);
        }
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        if(passengerBookings.containsKey(passengerId)){
            return passengerBookings.get(passengerId).size();
        }else{
            return 0;
        }
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        if(flights.containsKey(flightId)){
            for (Airport airport : airports.values()){
                if(flights.get(flightId).getFromCity().equals(airport.getCity())){
                    return airport.getAirportName();
                }
            }
        }
        return null;
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        int totalRevenue = 0;
        if(flightBookings.containsKey(flightId)){
            for (int i = 0; i < flightBookings.get(flightId).size(); i++) {
                totalRevenue += 3000 +(i * 50);
            }
        }
        return totalRevenue;
    }
}