package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {

    private static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of(" ", " "),
                Arguments.of("  ", "  "),
                Arguments.of("a", "a"),
                Arguments.of("ab", "ba"),
                Arguments.of("bab", "bab"),
                Arguments.of("-1", "1-"),
                Arguments.of("///22", "22///"),
                Arguments.of("happy path", "htap yppah")
        );
    }

    @ParameterizedTest
    @MethodSource(value = "getData")
    void reverseShouldThrowNPEWhenNullIsInput(String input, String reversed) {
        assertEquals(StringUtils.reverse(input), reversed);
    }

    @Test
    void reverseShouldThrowNPEWhenNullIsProvided() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> StringUtils.reverse(null));
    }

}
