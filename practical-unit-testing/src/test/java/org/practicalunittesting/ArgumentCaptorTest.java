package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArgumentCaptorTest {

    private static final int ONE_HOUR = 60;
    private static final Date START_DATE = new Date();
    private static final int MILLIS_IN_MINUTE = 1000 * 60;

    @Test
    void testingAndManipulatingArgumentCaptor() {
        ArgumentCaptor<Meeting> argumentCaptor = ArgumentCaptor.forClass(Meeting.class);

        Calendar calendar = mock(Calendar.class);


        PIM pim = new PIM(calendar);

        pim.addMeeting(START_DATE, ONE_HOUR);

        verify(calendar).addEvent(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getStartDate()).isEqualTo(START_DATE);
    }

}
