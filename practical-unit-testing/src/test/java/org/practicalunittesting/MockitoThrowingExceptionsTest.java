package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoThrowingExceptionsTest {

    private final Car myFerrari = mock(Car.class);

    @Test
    void throwException() {
        when(myFerrari.needsFuel()).thenThrow(new RuntimeException());
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(myFerrari::needsFuel);
    }

}
