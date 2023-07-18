package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexUtilsTest {

    private final int ANY_NUMBER_123 = 123;
    private final int ANY_NUMBER_321 = 321;

    @ParameterizedTest
    @ValueSource(strings = { "asd", "1", "12", "fsjlfsjl4", "dsdsds12" })
    void extractDigitsMustIgnoreLessThanThreeDigits(String input) {
        List<Integer> result = RegexUtils.extractDigits(input);
        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = { "123", "a123", "123v", "a123v" })
    void extractDigitsMustExtractThreeDigitsInARow(String input) {
        List<Integer> result = RegexUtils.extractDigits(input);
        assertThat(result).contains(ANY_NUMBER_123);
    }
    @ParameterizedTest
    @ValueSource(strings = { "123a321", "321a123", "v321a123v", "a123v321aa" })
    void extractDigitsMustExtractMultipleThreeDigitsInARow(String input) {
        List<Integer> result = RegexUtils.extractDigits(input);
        assertThat(result).contains(ANY_NUMBER_123);
        assertThat(result).contains(ANY_NUMBER_321);
    }

}
