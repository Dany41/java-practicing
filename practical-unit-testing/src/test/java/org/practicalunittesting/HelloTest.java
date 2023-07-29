package org.practicalunittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HelloTest {

    private Hello hello;
    private TimeProvider timeProvider;

    @BeforeEach
    void setUp() {
        timeProvider = mock(TimeProvider.class);
        hello = new Hello(timeProvider);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
    void shouldSayGoodMorningInTheMorning(int morningHour) {
        when(timeProvider.getTime())
                .thenReturn(getCalendar(morningHour));
        assertEquals("Good Morning!", hello.sayHello());
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23})
    void shouldSayGoodAfternoonInTheAfternoon(int morningHour) {
        when(timeProvider.getTime())
                .thenReturn(getCalendar(morningHour));
        assertEquals("Good Afternoon!", hello.sayHello());
    }

    private Calendar getCalendar(int hour) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        return cal;
    }
}