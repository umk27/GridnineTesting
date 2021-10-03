package com.gridnine.testing.flight_exceptions;

public class ArrivalUntilDepartureException extends FlightException {

    public ArrivalUntilDepartureException() {
        super();
    }

    public ArrivalUntilDepartureException(String message) {
        super(message);
    }

    public ArrivalUntilDepartureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrivalUntilDepartureException(Throwable cause) {
        super(cause);
    }

    public ArrivalUntilDepartureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
