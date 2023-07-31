package org.practicalunittesting;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {

    private static Faker faker = Faker.instance();

    private static Stream<Arguments> getData() {
        return Stream.generate(() -> {
            String random = faker.random().hex();
            List<Integer> chars = new java.util.ArrayList<>(random.chars().boxed().toList());
            Collections.reverse(chars);
            String reversed = chars.stream().map(i -> (char) i.intValue()).map(String::valueOf).collect(joining());
            return Arguments.of(random, reversed);
        }).limit(100);
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
