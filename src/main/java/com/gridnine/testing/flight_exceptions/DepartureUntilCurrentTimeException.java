package com.gridnine.testing.flight_exceptions;

public class DepartureUntilCurrentTimeException extends FlightException {

    public DepartureUntilCurrentTimeException() {
    }

    public DepartureUntilCurrentTimeException(String message) {
        super(message);
    }

    public DepartureUntilCurrentTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartureUntilCurrentTimeException(Throwable cause) {
        super(cause);
    }

    public DepartureUntilCurrentTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
