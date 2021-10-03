package com.gridnine.testing;





import com.gridnine.testing.Flight;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightFilter {

    public List<Flight> filterByDepartureTime(LocalDateTime time, List<Flight> flights);

    public List<Flight> filterByArrivalTime(LocalDateTime time, List<Flight> flights);

    public List<Flight> filterByTravelTime(Duration duration, List<Flight> flights);

    public List<Flight> filterByHasNotTransfer(List<Flight> flights);



}
