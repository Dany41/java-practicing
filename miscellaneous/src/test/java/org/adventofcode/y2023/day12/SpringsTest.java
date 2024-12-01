package org.adventofcode.y2023.day12;

import org.adventofcode.y2023.day12.Springs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SpringsTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "#.#.### 1,1,3",
            ".#...#....###. 1,1,3",
            ".#.###.#.###### 1,3,1,6",
            "####.#...#... 4,1,1",
            "#....######..#####. 1,6,5",
            ".###.##....# 3,2,1"
    })
    void arrangementsIsOneForSpringsWithoutUnknowns(String input) {
        Springs springs = new Springs(input);
        assertThat(springs.arrangements()).isOne();
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "???.### 1,1,3; 1",
            ".??..??...?##. 1,1,3; 4",
            "?#?#?#?#?#?#?#? 1,3,1,6; 1",
            "????.#...#... 4,1,1; 1",
            "????.######..#####. 1,6,5; 4",
            "?###???????? 3,2,1; 10"
    })
    void arrangementsIsCorrectForSpringsWithUnknowns(String input, int result) {
        Springs springs = new Springs(input);
        assertThat(springs.arrangements()).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            ".. 0!",
            "fhjsdkfhjks",
            "fhj???fhjks",
    })
    void springsCreationThrowsExceptionForInvalidInputs(String input) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Springs(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "#...# 1 3",
            "#...## 1 3"
    })
    void springsAreInvalidForUnknowns(String input) {
        Springs springs = new Springs(input);
        assertThat(springs.validity()).isEqualTo(Springs.Validity.INVALID);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "#.#.### 1,1,3",
            ".#...#....###. 1,1,3",
            ".#.###.#.###### 1,3,1,6",
            "####.#...#... 4,1,1",
            "#....######..#####. 1,6,5",
            ".###.##....# 3,2,1"
    })
    void springsAreValidWithoutUnknowns(String input) {
        Springs springs = new Springs(input);
        assertThat(springs.validity()).isEqualTo(Springs.Validity.VALID);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "???.### 1,1,3",
            ".??..??...?##. 1,1,3",
            "?#?#?#?#?#?#?#? 1,3,1,6",
            "????.#...#... 4,1,1",
            "????.######..#####. 1,6,5",
            "?###???????? 3,2,1"
    })
    void springsAreUndefinedIfValidButWithUnknowns(String input) {
        Springs springs = new Springs(input);
        assertThat(springs.validity()).isEqualTo(Springs.Validity.UNDEFINED);
    }

}