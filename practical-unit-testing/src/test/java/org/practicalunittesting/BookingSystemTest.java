package org.practicalunittesting;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookingSystemTest {

    private BookingSystem bookingSystem;

    @BeforeEach
    void setUp() {
        bookingSystem = new BookingSystem();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void bookingSystemMustBookValidHours(int validHour) {
        bookingSystem.book(validHour);
        assertThat(bookingSystem.getBookedHours()).contains(validHour);
    }

    @ParameterizedTest
    @ValueSource(ints = {-999, -1, 24, 999})
    void bookingSystemMustThrowExceptionForInvalidHours(int invalidHour) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> bookingSystem.book(invalidHour));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void bookingSystemMustThrowExceptionWhenBookBookedHour(int validHour) {
        assertThatExceptionOfType(HourIsAlreadyBooked.class)
                .isThrownBy(() -> {
                    bookingSystem.book(validHour);
                    bookingSystem.book(validHour);
                });
    }



}
