package com.gridnine.testing.flight_exceptions;

public class TimeOnEarthExceedsTwoHoursException extends FlightException {

    public TimeOnEarthExceedsTwoHoursException() {
        super();
    }

    public TimeOnEarthExceedsTwoHoursException(String message) {
        super(message);
            }

    public TimeOnEarthExceedsTwoHoursException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeOnEarthExceedsTwoHoursException(Throwable cause) {
        super(cause);
    }

    public TimeOnEarthExceedsTwoHoursException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
