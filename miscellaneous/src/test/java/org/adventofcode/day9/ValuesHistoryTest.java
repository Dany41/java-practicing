package org.adventofcode.day9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ValuesHistoryTest {

    @Test
    void predictNextValueOnEmptyValueHistoryShouldReturnNegativeOne() {
        ValuesHistory valuesHistory = new ValuesHistory("");
        assertThat(valuesHistory.predictNextValue()).isEqualTo(-1);
    }

    @Test
    void predictPrevValueOnEmptyValueHistoryShouldReturnNegativeOne() {
        ValuesHistory valuesHistory = new ValuesHistory("");
        assertThat(valuesHistory.predictPreviousValue()).isEqualTo(-1);
    }

    @Test
    void predictNextValueReturnsZeroForZeroSequence() {
        ValuesHistory valuesHistory = new ValuesHistory("0 0 0 0 0 0 0 0");
        assertThat(valuesHistory.predictNextValue()).isZero();
    }

    @Test
    void predictPrevValueReturnsZeroForZeroSequence() {
        ValuesHistory valuesHistory = new ValuesHistory("0 0 0 0 0 0 0 0");
        assertThat(valuesHistory.predictPreviousValue()).isZero();
    }

    @ParameterizedTest
    @CsvSource({
            "1 1 1 1 1 1, 1",
            "2 2 2 2 2 2, 2",
            "-1 -1 -1 -1, -1",
            "99 99 99 99, 99"
    })
    void predictNextSameValueForSequencesWithAllEqualsNumbers(String input, int value) {
        ValuesHistory valuesHistory = new ValuesHistory(input);
        assertThat(valuesHistory.predictNextValue()).isEqualTo(value);
    }

    @ParameterizedTest
    @CsvSource({
            "1 1 1 1 1 1, 1",
            "2 2 2 2 2 2, 2",
            "-1 -1 -1 -1, -1",
            "99 99 99 99, 99"
    })
    void predictPrevSameValueForSequencesWithAllEqualsNumbers(String input, int value) {
        ValuesHistory valuesHistory = new ValuesHistory(input);
        assertThat(valuesHistory.predictPreviousValue()).isEqualTo(value);
    }

    @ParameterizedTest
    @CsvSource({
            "0 3 6 9 12 15, 18",
            "1 3 6 10 15 21, 28",
            "10 13 16 21 30 45, 68"
    })
    void predictNextCorrectValueForMultilevelSequences(String input, int value) {
        ValuesHistory valuesHistory = new ValuesHistory(input);
        assertThat(valuesHistory.predictNextValue()).isEqualTo(value);
    }

    @ParameterizedTest
    @CsvSource({
            "0 3 6 9 12 15, -3",
            "1 3 6 10 15 21, 0",
            "10 13 16 21 30 45, 5"
    })
    void predictPrevCorrectValueForMultilevelSequences(String input, int value) {
        ValuesHistory valuesHistory = new ValuesHistory(input);
        assertThat(valuesHistory.predictPreviousValue()).isEqualTo(value);
    }

}