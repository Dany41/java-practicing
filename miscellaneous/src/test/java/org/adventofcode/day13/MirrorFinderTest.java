package org.adventofcode.day13;

import org.adventofcode.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MirrorFinderTest {

    @ParameterizedTest
    @MethodSource("isMirrorValidCases")
    void isMirrorReturnsTrueForValidCases(int index, String[] strings) {
        char[][] input = Utils.stringArrayToChars(strings);
        assertThat(MirrorFinder.isMirror(index, input)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isMirrorInvalidCases")
    void isMirrorReturnsFalseForInvalidCases(int index, String[] strings) {
        char[][] input = Utils.stringArrayToChars(strings);
        assertThat(MirrorFinder.isMirror(index, input)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("isMirrorValidCasesWithDiffOne")
    void isMirrorReturnsTrueForValidCasesWithDiffOne(int index, String[] strings) {
        char[][] input = Utils.stringArrayToChars(strings);
        assertThat(MirrorFinder.isMirror(index, input, 1)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isMirrorInvalidCasesWithDiffOne")
    void isMirrorReturnsFalseForInvalidCasesWithDiffOne(int index, String[] strings) {
        char[][] input = Utils.stringArrayToChars(strings);
        assertThat(MirrorFinder.isMirror(index, input, 1)).isFalse();
    }

    private static Stream<Arguments> isMirrorValidCases() {
        return Stream.of(
                Arguments.of(0, new String[]{"11", "11", "22"}),
                Arguments.of(2, new String[]{"11", "22", "33", "33"}),
                Arguments.of(1, new String[]{"11", "22", "22"}),
                Arguments.of(3, new String[]{"11", "22", "22", "33", "33", "22"})
        );
    }

    private static Stream<Arguments> isMirrorInvalidCases() {
        return Stream.of(
                Arguments.of(1, new String[]{"11", "11", "22"}),
                Arguments.of(1, new String[]{"11", "22", "33", "33"}),
                Arguments.of(2, new String[]{"11", "22", "22"}),
                Arguments.of(2, new String[]{"11", "22", "33", "33", "22", "00"}),
                Arguments.of(5, new String[]{"11", "22", "22", "33", "33", "22"})
        );
    }

    private static Stream<Arguments> isMirrorValidCasesWithDiffOne() {
        return Stream.of(
                Arguments.of(2, new String[]{"#.##..##.", "..#.##.#.", "##......#", "##......#", "..#.##.#.", "..##..##.", "#.#.##.#."}),
                Arguments.of(0, new String[]{"#...##..#", "#....#..#", "..##..###", "#####.##.", "#####.##.", "..##..###", "#....#..#"})
        );
    }

    private static Stream<Arguments> isMirrorInvalidCasesWithDiffOne() {
        return Stream.of(
                Arguments.of(0, new String[]{"#.##..##.", "..#.##.#.", "##......#", "##......#", "..#.##.#.", "..##..##.", "#.#.##.#."}),
                Arguments.of(1, new String[]{"#.##..##.", "..#.##.#.", "##......#", "##......#", "..#.##.#.", "..##..##.", "#.#.##.#."}),
                Arguments.of(3, new String[]{"#.##..##.", "..#.##.#.", "##......#", "##......#", "..#.##.#.", "..##..##.", "#.#.##.#."}),
                Arguments.of(4, new String[]{"#.##..##.", "..#.##.#.", "##......#", "##......#", "..#.##.#.", "..##..##.", "#.#.##.#."}),
                Arguments.of(5, new String[]{"#.##..##.", "..#.##.#.", "##......#", "##......#", "..#.##.#.", "..##..##.", "#.#.##.#."}),
                Arguments.of(6, new String[]{"#.##..##.", "..#.##.#.", "##......#", "##......#", "..#.##.#.", "..##..##.", "#.#.##.#."}),
                Arguments.of(1, new String[]{"#...##..#", "#....#..#", "..##..###", "#####.##.", "#####.##.", "..##..###", "#....#..#"}),
                Arguments.of(2, new String[]{"#...##..#", "#....#..#", "..##..###", "#####.##.", "#####.##.", "..##..###", "#....#..#"}),
                Arguments.of(4, new String[]{"#...##..#", "#....#..#", "..##..###", "#####.##.", "#####.##.", "..##..###", "#....#..#"}),
                Arguments.of(5, new String[]{"#...##..#", "#....#..#", "..##..###", "#####.##.", "#####.##.", "..##..###", "#....#..#"}),
                Arguments.of(6, new String[]{"#...##..#", "#....#..#", "..##..###", "#####.##.", "#####.##.", "..##..###", "#....#..#"})
        );
    }
}