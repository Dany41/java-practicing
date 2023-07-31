package org.practicalunittesting;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.argThat;

class PIMTest {

    private static final int ONE_HOUR = 60;
    private static final Date START_DATE = new Date();
    private static final int MILLIS_IN_MINUTE = 1000 * 60;
    private static final Date END_DATE = new Date(START_DATE.getTime()
            + ONE_HOUR * MILLIS_IN_MINUTE);

    @Test
    void shouldAddNewEventToCalendar() {
        Calendar calendar = mock(Calendar.class);
        PIM pim = new PIM(calendar);
        Meeting expectedMeeting = new Meeting(START_DATE, END_DATE);

        pim.addMeeting(START_DATE, ONE_HOUR);

        // requires equals() method to be valid
        verify(calendar).addEvent(expectedMeeting);
    }

    @Test
    void shouldAddNewEventToCalendar_ArgumentCaptor() {
        Calendar calendar = mock(Calendar.class);
        PIM pim = new PIM(calendar);
        ArgumentCaptor<Meeting> argument = ArgumentCaptor.forClass(Meeting.class);

        pim.addMeeting(START_DATE, ONE_HOUR);

        verify(calendar).addEvent(argument.capture());
        Meeting meeting = argument.getValue();

        assertThat(meeting.getStartDate()).isEqualTo(START_DATE);
        assertThat(meeting.getEndDate()).isEqualTo(END_DATE);
    }

    @Test
    void shouldAddNewEventToCalendar_HamcrestMatchers() {

        Calendar calendar = mock(Calendar.class);
        PIM pim = new PIM(calendar);

        pim.addMeeting(START_DATE, ONE_HOUR);

        verify(calendar).addEvent(argThat(event ->
                event.getStartDate().equals(START_DATE) &&
                event.getEndDate().equals(END_DATE)
        ));

    }



}