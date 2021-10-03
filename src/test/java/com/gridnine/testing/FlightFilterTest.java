package com.gridnine.testing;


import com.gridnine.testing.flight_exceptions.DepartureUntilCurrentTimeException;
import com.gridnine.testing.flight_exceptions.FlightException;
import com.gridnine.testing.flight_exceptions.TimeOnEarthExceedsTwoHoursException;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightFilterImpl;

import static org.junit.Assert.*;


@RunWith(Enclosed.class)
public class FlightFilterTest {

    static FlightFilterImpl flightFilter;

    static List<Flight> flights1;

    static List<Flight> flights2;

    static List<Flight> flights3;

    static List<Flight> flights4;

    static Flight f1;

    static Flight f2;

    static Flight f3;

    static Flight f4;

    static Flight f5;

    static Flight f6;

    @BeforeClass
    public static void init() {

        flightFilter = new FlightFilterImpl();
    }

    public static void testValueBuilder() {
        Segment segment1 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4));
        List<Segment> segments1 = new ArrayList<>();
        segments1.add(segment1);
        f1 = new Flight(segments1);

        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6));
        List<Segment> segments2 = new ArrayList<>();
        segments2.add(segment2);
        segments2.add(segment3);
        f2 = new Flight(segments2);

        Segment segment4 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));
        Segment segment5 = new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5));
        Segment segment6 = new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(7));
        List<Segment> segments3 = new ArrayList<>();
        segments3.add(segment4);
        segments3.add(segment5);
        segments3.add(segment6);
        f3 = new Flight(segments3);


        Segment segment7 = new Segment(LocalDateTime.now().minusHours(2), LocalDateTime.now().plusHours(4));
        List<Segment> segments4 = new ArrayList<>();
        segments4.add(segment7);
        f4 = new Flight(segments4);

        Segment segment8 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().minusHours(2));
        List<Segment> segments5 = new ArrayList<>();
        segments5.add(segment8);
        f5 = new Flight(segments5);

        Segment segment9 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));
        Segment segment10 = new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6));
        List<Segment> segments6 = new ArrayList<>();
        segments6.add(segment9);
        segments6.add(segment10);
        f6 = new Flight(segments6);

        flights1 = List.of(f1, f2, f3);

        flights2 = List.of(f1, f2, f3, f4);

        flights3 = List.of(f1, f2, f3, f5);

        flights4 = List.of(f1, f2, f3, f6);

    }

    @RunWith(Parameterized.class)
    public static class filterByDepartureTime {

        @Parameterized.Parameter(0)
        public LocalDateTime time;
        @Parameterized.Parameter(1)
        public List<Flight> flights;
        @Parameterized.Parameter(2)
        public List<Flight> flightsRes;

        @Parameterized.Parameters
        public static Iterable<Object> dataForTest() {
            testValueBuilder();
            return Arrays.asList(new Object[][]{
                    {LocalDateTime.now().plusHours(1), flights1, flights1},
                    {LocalDateTime.now().plusHours(3), flights1, List.of(f2)},
            });
        }

        @Test
        public void filterByDepartureTime() {
            assertEquals(flightsRes, flightFilter.filterByDepartureTime(time, flights));
        }

    }

    @RunWith(Parameterized.class)
    public static class filterByArrivalTime {

        @Parameterized.Parameter(0)
        public LocalDateTime time;
        @Parameterized.Parameter(1)
        public List<Flight> flights;
        @Parameterized.Parameter(2)
        public List<Flight> flightsRes;

        @Parameterized.Parameters
        public static Iterable<Object> dataForTest() {
            testValueBuilder();
            return Arrays.asList(new Object[][]{
                    {LocalDateTime.now().plusHours(5), flights1, List.of(f1)},
                    {LocalDateTime.now().plusHours(6), flights1, List.of(f1, f2)},
                    {LocalDateTime.now().plusHours(8), flights1, List.of(f1, f2, f3)},
            });
        }

        @Test
        public void filterByArrivalTime() {
            assertEquals(flightsRes, flightFilter.filterByArrivalTime(time, flights));
        }

    }

    @RunWith(Parameterized.class)
    public static class filterbyTravelTime {

        @Parameterized.Parameter(0)
        public Duration time;
        @Parameterized.Parameter(1)
        public List<Flight> flights;
        @Parameterized.Parameter(2)
        public List<Flight> flightsRes;

        @Parameterized.Parameters
        public static Iterable<Object> dataForTest() {
            testValueBuilder();
            return Arrays.asList(new Object[][]{
                    {Duration.ofHours(2), flights1, List.of(f1)},
                    {Duration.ofHours(4), flights1, List.of(f1, f2)},
                    {Duration.ofHours(5), flights1, List.of(f1, f2, f3)},
            });
        }

        @Test
        public void filterByTravelTime() {
            assertEquals(flightsRes, flightFilter.filterByTravelTime(time, flights));
        }


    }

    @RunWith(Parameterized.class)
    public static class filterByHasNotTransfer {

        @Parameterized.Parameter(0)
        public List<Flight> flights;
        @Parameterized.Parameter(1)
        public List<Flight> flightsRes;

        @Parameterized.Parameters
        public static Iterable<Object> dataForTest() {
            testValueBuilder();
            return Arrays.asList(new Object[][]{
                    {flights1, List.of(f1)},
            });
        }

        @Test
        public void filterByHasNotTransfer() {
            assertEquals(flightsRes, flightFilter.filterByHasNotTransfer(flights));
        }

    }

    public static class CheckExceptionTest {
        @Test(expected = DepartureUntilCurrentTimeException.class)
        public void filterByDeparture() {
          assertEquals(flights1, flightFilter.filterByDepartureTime(LocalDateTime.now().plusHours(1), flights2));


        }

        @Test(expected = DepartureUntilCurrentTimeException.class)
        public void filterByTravelTimeFirst() {
            assertEquals(flights1, flightFilter.filterByTravelTime(Duration.ofHours(5), flights3));
        }

        @Test(expected = TimeOnEarthExceedsTwoHoursException.class)
        public void filterByTravelTimeSecond() {
            assertEquals(flights1, flightFilter.filterByTravelTime(Duration.ofHours(5), flights4));
        }

    }

    @AfterClass
    public static void destroy() {
        flightFilter = null;
    }
}