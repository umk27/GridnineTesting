package com.gridnine.testing;


import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FlightBuilder flightBuilder = new FlightBuilder();

        FlightExceptionRemover flightExceptionRemover = new FlightExceptionRemoverImpl();

        List<Flight> flights = flightBuilder.createFlights();
        System.out.println("Исходный список перелетов:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
        System.out.println("-------------------");


        flights = flightExceptionRemover.removeFlightIfDepartureUntilCurrentTime(flights);
        flights = flightExceptionRemover.removeFlightIfArrivalUntilDeparture(flights);
        flights = flightExceptionRemover.removeFlightIfTimeOnEarthExceedsTwoHours(flights);
        System.out.println("Список перелетов после удаления перелетов, которые необходимо исключить:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
        System.out.println("-------------------");


        FlightFilter filter = new FlightFilterImpl();

        List<Flight> flights1 = filter.filterByDepartureTime(LocalDateTime.now().plusDays(1), flights);
        System.out.println("Список перелетов после сортировки по времени вылета:");
        for (Flight flight : flights1) {
            System.out.println(flight);
        }
        System.out.println("-------------------");


        List<Flight> flights2 = filter.filterByArrivalTime(LocalDateTime.now().plusDays(3).plusHours(5), flights);
        System.out.println("Список перелетов после сортировки по времени прилета:");
        for (Flight flight : flights2) {
            System.out.println(flight);
        }
        System.out.println("-------------------");


        List<Flight> flights3 = filter.filterByTravelTime(Duration.ofHours(6), flights);
        System.out.println("Список перелетов после сортировки по длительности перелета:");
        for (Flight flight : flights3) {
            System.out.println(flight);
        }
        System.out.println("-------------------");


        List<Flight> flights4 =filter.filterByHasNotTransfer(flights);
        System.out.println("Список перелетов без пересадок:");
        for (Flight flight : flights4) {
            System.out.println(flight);
        }
        System.out.println("-------------------");


    }
}
