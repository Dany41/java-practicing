package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookingSystemRevisitedTest {

    private Classroom classroomA = mock(Classroom.class);
    private Classroom classroomB = mock(Classroom.class);
    private List<Classroom> classrooms = Arrays.asList(classroomA, classroomB);

    @Test
    void listAllShouldReturnAllTheExistingClassrooms() {
        BookingSystemRevisited bookingSystem = new BookingSystemRevisited(classrooms);
        assertThat(bookingSystem.listAll()).containsExactlyElementsOf(classrooms);
    }

    @Test
    void listAvailableShouldProvideOnlyAvailableClassrooms() {
        BookingSystemRevisited bookingSystem = new BookingSystemRevisited(classrooms);
        when(classroomA.getName()).thenReturn("A");
        when(classroomB.getName()).thenReturn("B");


        boolean booked = bookingSystem.book("A", DayOfWeek.FRIDAY, 17);

        assertThat(booked).isTrue();
        assertThat(bookingSystem.listAvailable(DayOfWeek.FRIDAY, 17))
                .doesNotContain(classroomA)
                .contains(classroomB);

    }
}