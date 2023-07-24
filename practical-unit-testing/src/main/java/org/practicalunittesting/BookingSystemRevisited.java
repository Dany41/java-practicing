package org.practicalunittesting;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingSystemRevisited {

    private List<Classroom> classrooms;

    private Map<String, List<Pair<DayOfWeek, Integer>>> bookings = new HashMap<>();

    public BookingSystemRevisited(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<Classroom> listAll() {
        return this.classrooms;
    }

    public boolean book(String classroomName, DayOfWeek dayOfWeek, int hour) {
        boolean bookedBefore = bookings.containsKey(classroomName);
        Pair<DayOfWeek, Integer> bookingDetails = Pair.of(dayOfWeek, hour);
        if (bookedBefore && bookings.get(classroomName).contains(bookingDetails)) {
            return false;
        } else {
            if (bookedBefore) {
                bookings.get(classroomName).add(bookingDetails);
            } else {
                List<Pair<DayOfWeek, Integer>> bookingDetailsList = new ArrayList<>();
                bookingDetailsList.add(bookingDetails);
                bookings.put(classroomName, bookingDetailsList);
            }
            return true;
        }
    }

    public List<Classroom> listAvailable(DayOfWeek dayOfWeek, int hour) {
        return classrooms.stream()
                .filter(classroom -> !(bookings.containsKey(classroom.getName())
                        && bookings.get(classroom.getName()).contains(Pair.of(dayOfWeek, hour))))
                .collect(Collectors.toList());
    }
}
