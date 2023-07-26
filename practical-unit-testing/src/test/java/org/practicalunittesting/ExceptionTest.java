package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ExceptionTest {

    @Test
    void testException() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> {
                    throw new Exception("message");
                })
                .withMessage("message")
                .withMessageEndingWith("age")
                .withMessageContaining("mes")
                .withMessageContainingAll("m", "e", "s")
                .withNoCause();
    }

}
