package org.practicalunittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookingSystemRevisitedTest {

    private Classroom classroomA = mock(Classroom.class);
    private Classroom classroomB = mock(Classroom.class);
    private List<Classroom> classrooms = Arrays.asList(classroomA, classroomB);

    @BeforeEach
    void setUp() {
        when(classroomA.getName()).thenReturn("A");
        when(classroomA.getEquipment()).thenReturn(List.of(Equipment.PROJECTOR));
        when(classroomA.getCapacity()).thenReturn(25);
        when(classroomA.getCleaningHour()).thenReturn(10);

        when(classroomB.getName()).thenReturn("B");
        when(classroomB.getEquipment()).thenReturn(List.of(Equipment.PROJECTOR));
        when(classroomB.getCapacity()).thenReturn(15);
        when(classroomB.getCleaningHour()).thenReturn(18);
    }

    @Test
    void listAllShouldReturnAllTheExistingClassrooms() {
        BookingSystemRevisited bookingSystem = new BookingSystemRevisited(classrooms);
        assertThat(bookingSystem.listAll()).containsExactlyElementsOf(classrooms);
    }

    @Test
    void listAvailableShouldProvideOnlyAvailableClassrooms() {
        BookingSystemRevisited bookingSystem = new BookingSystemRevisited(classrooms);

        boolean booked = bookingSystem.book("A", DayOfWeek.FRIDAY, 17);

        assertThat(booked).isTrue();
        assertThat(bookingSystem.listAvailable(DayOfWeek.FRIDAY, 17))
                .doesNotContain(classroomA)
                .contains(classroomB);

    }

    @Test
    void shouldBookByPeopleAndEquipmentParameters() {
        BookingSystemRevisited bookingSystem = new BookingSystemRevisited(classrooms);

        boolean booked = bookingSystem.book(20, Equipment.PROJECTOR, DayOfWeek.THURSDAY, 11);

        assertThat(booked).isTrue();
        assertThat(bookingSystem.listAvailable(DayOfWeek.THURSDAY, 11))
                .doesNotContain(classroomA)
                .contains(classroomB);
    }

    @Test
    void classroomsShouldNotBeAvailableAtCleaningHours() {
        BookingSystemRevisited bookingSystem = new BookingSystemRevisited(classrooms);

        boolean bookedA = bookingSystem.book("A", DayOfWeek.MONDAY, 10);
        boolean bookedB = bookingSystem.book("B", DayOfWeek.MONDAY, 18);

        assertThat(bookedA).isFalse();
        assertThat(bookedB).isFalse();
        assertThat(bookingSystem.listAvailable(DayOfWeek.MONDAY, 10))
                .doesNotContain(classroomA)
                .contains(classroomB);
        assertThat(bookingSystem.listAvailable(DayOfWeek.MONDAY, 18))
                .doesNotContain(classroomB)
                .contains(classroomA);
    }
}