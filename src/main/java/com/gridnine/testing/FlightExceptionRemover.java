package com.gridnine.testing;

import java.util.List;

public interface FlightExceptionRemover {

    public List<Flight> removeFlightIfDepartureUntilCurrentTime(List<Flight> flights);

    public List<Flight> removeFlightIfArrivalUntilDeparture(List<Flight> flights);

    public List<Flight> removeFlightIfTimeOnEarthExceedsTwoHours(List<Flight> flights);

}
