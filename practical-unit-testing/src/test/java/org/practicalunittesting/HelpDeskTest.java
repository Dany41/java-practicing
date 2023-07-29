package org.practicalunittesting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HelpDeskTest {

    TimeProvider timeProvider = mock(TimeProvider.class);
    HelpDesk helpDesk = new HelpDesk(timeProvider);
    Issue issue = mock(Issue.class);

    private static Stream<Arguments> getWorkingDaysAndHours() {
        return Stream.concat(
                Stream.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY)
                        .flatMap(dayOfWeek -> IntStream.range(0, 24).mapToObj(i -> Arguments.of(dayOfWeek, i))),
                IntStream.range(0, 17).mapToObj(i -> Arguments.of(DayOfWeek.FRIDAY, i))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "getWorkingDaysAndHours")
    void issueIsHandledIfWorkingDaysAndHours(DayOfWeek workingDay, int hour) {
        when(timeProvider.getTime()).thenReturn(getCalendar(workingDay, hour));
        assertThat(helpDesk.willHandleIssue(issue)).isTrue();
    }


    private static Stream<Arguments> getNotWorkingDaysAndHours() {
        return Stream.concat(
                Stream.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
                        .flatMap(dayOfWeek -> IntStream.range(0, 24).mapToObj(i -> Arguments.of(dayOfWeek, i))),
                IntStream.range(18, 24).mapToObj(i -> Arguments.of(DayOfWeek.FRIDAY, i))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "getNotWorkingDaysAndHours")
    void issueIsNotHandledIfNotWorkingDaysAndHours(DayOfWeek notWorkingDay, int hour) {
        when(timeProvider.getTime()).thenReturn(getCalendar(notWorkingDay, hour));
        assertThat(helpDesk.willHandleIssue(issue)).isFalse();
    }
    private Calendar getCalendar(DayOfWeek day, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day.getValue() + 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar;
    }
}