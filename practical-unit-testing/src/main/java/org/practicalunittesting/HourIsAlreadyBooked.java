package org.practicalunittesting;

public class HourIsAlreadyBooked extends RuntimeException {

    public HourIsAlreadyBooked(String message) {
        super(message);
    }

    public HourIsAlreadyBooked(String message, Throwable cause) {
        super(message, cause);
    }

}
