package com.gridnine.testing;

import com.gridnine.testing.flight_exceptions.ArrivalUntilDepartureException;
import com.gridnine.testing.flight_exceptions.DepartureUntilCurrentTimeException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightFilterImpl implements FlightFilter {

    @Override
    public List<Flight> filterByDepartureTime(LocalDateTime time, List<Flight> flights) {

        flights = flights.stream().filter(flight -> {
            List<Segment> segments = flight.getSegments();
            Segment segment = segments.get(0);
            Duration duration = Duration.between(time, segment.getDepartureDate());
            Duration durationEx = Duration.between(LocalDateTime.now(), segment.getDepartureDate());
            if (durationEx.toMinutes() < 0) {
                try {
                    throw new DepartureUntilCurrentTimeException("Departure is until current time");
                } catch (DepartureUntilCurrentTimeException e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
              //      System.exit(1);
                }
            }
            if (duration.toMinutes() < 0) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        return flights;
    }

    @Override
    public List<Flight> filterByArrivalTime(LocalDateTime time, List<Flight> flights) {

        flights = flights.stream().filter(flight -> {
            List<Segment> segments = flight.getSegments();
            Segment segment = segments.get(segments.size() - 1);
            Duration duration = Duration.between(segment.getArrivalDate(), time);

            if (duration.toMinutes() < 0) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        return flights;
    }

    @Override
    public List<Flight> filterByTravelTime(Duration duration, List<Flight> flights) {

        flights = flights.stream().filter(flight -> {
            List<Segment> segments = flight.getSegments();
            Segment segment1 = segments.get(0);
            Segment segment2 = segments.get(segments.size() - 1);
            Duration duration1 = Duration.between(segment1.getDepartureDate(), segment2.getArrivalDate());
            Duration durationEx2 = Duration.between(LocalDateTime.now(), segment1.getDepartureDate());
            if (segments.size() > 1) {
                for (int j = 1; j < segments.size(); j++) {

                    Segment segmentExx1 = segments.get(j - 1);
                    Segment segmentExx2 = segments.get(j);
                    Duration duration2 = Duration.between(segmentExx1.getArrivalDate(), segmentExx2.getDepartureDate());

                    if (duration2.toHours() > 2) {
                        return false;
                    }
                }
                if (duration1.toMinutes() < 0) {
                    try {
                        throw new ArrivalUntilDepartureException("Time on earth exceeds two hours");
                    } catch (ArrivalUntilDepartureException e) {
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                  //      System.exit(1);
                    }
                }
            }
            if (durationEx2.toMinutes() < 0) {
                try {
                    throw new DepartureUntilCurrentTimeException("Departure is until current time");
                } catch (DepartureUntilCurrentTimeException e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                 //   System.exit(1);
                }
            }

            if (duration.toMinutes() < duration1.toMinutes()) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        return flights;
    }

    @Override
    public List<Flight> filterByHasNotTransfer(List<Flight> flights) {

        flights = flights.stream().filter(flight -> {
            List<Segment> segments = flight.getSegments();
            if (segments.size() > 1) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        return flights;
    }
}
