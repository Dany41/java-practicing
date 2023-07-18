package org.practicalunittesting;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {

    List<Integer> bookedHours = new ArrayList<>();

    public void book(int hourToBook) {
        if (hourToBook < 0 || hourToBook > 23)
            throw new IllegalArgumentException(
                    String.format("Provided hour '%o' is invalid, allowed hours are in range [0, 23]", hourToBook));

        if (bookedHours.contains(hourToBook))
            throw new HourIsAlreadyBooked(String.format("Hour '%o' is already booked", hourToBook));

        bookedHours.add(hourToBook);
    }

    public List<Integer> getBookedHours() {
        return bookedHours;
    }
}
