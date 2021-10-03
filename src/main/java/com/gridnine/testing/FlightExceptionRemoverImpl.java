package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightExceptionRemoverImpl implements FlightExceptionRemover {


    @Override
    public List<Flight> removeFlightIfDepartureUntilCurrentTime(List<Flight> flights) {

        flights = flights.stream().filter(flight -> {
            List<Segment> segments = flight.getSegments();
            Segment segment = segments.get(0);
            Duration duration = Duration.between(LocalDateTime.now(), segment.getDepartureDate());
            if (duration.toMinutes() < 0) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        return flights;
    }

    @Override
    public List<Flight> removeFlightIfArrivalUntilDeparture(List<Flight> flights) {

     flights =  flights.stream().filter(flight -> {
         List<Segment> segments = flight.getSegments();
         for (int i = 0; i < segments.size(); i++) {

             Segment segment = segments.get(i);
             Duration duration = Duration.between(segment.getDepartureDate(), segment.getArrivalDate());

             if (duration.toMinutes() < 0) {
                 return false;
             }
         }
         return true;
     }).collect(Collectors.toList());

        return flights;
    }

    @Override
    public List<Flight> removeFlightIfTimeOnEarthExceedsTwoHours(List<Flight> flights) {

       flights = flights.stream().filter(flight -> {
           List<Segment> segments = flight.getSegments();
           if (segments.size() > 1) {
               for (int j = 1; j < segments.size(); j++) {

                   Segment segment1 = segments.get(j - 1);
                   Segment segment2 = segments.get(j);
                   Duration duration = Duration.between(segment1.getArrivalDate(), segment2.getDepartureDate());

                   if (duration.toHours() >2) {
                      return false;
                   }
               }
           }
           return true;
       }).collect(Collectors.toList());

        return flights;
    }
}
