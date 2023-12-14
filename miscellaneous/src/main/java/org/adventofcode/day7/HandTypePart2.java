package org.adventofcode.day7;

import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum HandTypePart2 {

    FIVE_OF_A_KIND(7, chars -> {
        char[] charsWithoutJ = charsWithoutJ(chars);
        Set<Character> uniqueChars = uniqueChars(chars);
        int jokerCharsCount = countCharInChars(chars, 'J');
        Set<Integer> result = uniqueChars.stream().map(c -> countCharInChars(charsWithoutJ, c)).collect(Collectors.toSet());
        return result.contains(5) || result.stream().max(Integer::compareTo).orElse(0) + jokerCharsCount == 5;
    }),
    FOUR_OF_A_KIND(6, chars -> {
        char[] charsWithoutJ = charsWithoutJ(chars);
        Set<Character> uniqueChars = uniqueChars(chars);
        int jokerCharsCount = countCharInChars(chars, 'J');
        Set<Integer> result = uniqueChars.stream().map(c -> countCharInChars(charsWithoutJ, c)).collect(Collectors.toSet());
        return result.contains(4) || result.stream().max(Integer::compareTo).orElse(0) + jokerCharsCount == 4;
    }),
    FULL_HOUSE(5, chars -> {
        char[] charsWithoutJ = charsWithoutJ(chars);
        Set<Character> uniqueChars = uniqueChars(charsWithoutJ);
        int jokerCharsCount = countCharInChars(chars, 'J');
        List<Integer> result = uniqueChars.stream().map(c -> countCharInChars(charsWithoutJ, c)).toList();
        long countOfPairs = result.stream().filter(i -> i == 2).count();
        return result.contains(2) && result.contains(3) || countOfPairs == 2 && jokerCharsCount == 1;
    }),
    THREE_OF_A_KIND(4, chars -> {
        char[] charsWithoutJ = charsWithoutJ(chars);
        Set<Character> uniqueChars = uniqueChars(charsWithoutJ);
        int jokerCharsCount = countCharInChars(chars, 'J');
        Set<Integer> result = uniqueChars.stream().map(c -> countCharInChars(charsWithoutJ, c)).collect(Collectors.toSet());
        int max = result.stream().max(Integer::compare).orElse(0);
        return result.contains(3) || max + jokerCharsCount == 3;
    }),
    TWO_PAIR(3, chars -> {
        char[] charsWithoutJ = charsWithoutJ(chars);
        Set<Character> uniqueChars = uniqueChars(chars);
        int jokerCharsCount = countCharInChars(chars, 'J');
        List<Integer> result = uniqueChars.stream().map(c -> countCharInChars(charsWithoutJ, c)).toList();
        return result.stream().filter(i -> i == 2).count() == 2 || jokerCharsCount == 2;
    }),
    ONE_PAIR(2, chars -> {
        char[] charsWithoutJ = charsWithoutJ(chars);
        Set<Character> uniqueChars = uniqueChars(chars);
        List<Integer> result = uniqueChars.stream().map(c -> countCharInChars(charsWithoutJ, c)).toList();
        int max = result.stream().max(Integer::compare).orElse(0);
        int jokerCharsCount = countCharInChars(chars, 'J');
        return result.stream().filter(i -> i == 2).count() == 1 && max == 2 || jokerCharsCount == 1;
    }),
    HIGH_CARD(1, chars -> {
        Set<Character> uniqueChars = uniqueChars(chars);
        List<Integer> result = uniqueChars.stream().map(c -> countCharInChars(chars, c)).toList();
        return result.stream().allMatch(i -> i == 1);
    });

    private static char[] charsWithoutJ(char[] chars) {
        char[] result = new char[chars.length - countCharInChars(chars, 'J')];
        for (int i = 0, j = 0; i < chars.length; i++) {
            if (chars[i] != 'J') {
                result[j] = chars[i];
                j++;
            }
        }
        return result;
    }

    private static Set<Character> uniqueChars(char[] chars) {
        Set<Character> uniqueChars = new HashSet<>();
        for (char aChar : chars) uniqueChars.add(aChar);
        return uniqueChars;
    }

    private static Boolean charsHaveExactAmountOfInputChar(char[] chars, char aChar, int count) {
        int countMatch = countCharInChars(chars, aChar);
        return count <= countMatch;
    }

    private static int countCharInChars(char[] chars, char aChar) {
        int matchCount = 0;
        for (char c : chars) if (c == aChar) matchCount++;
        return matchCount;
    }

    private final int strength;
    private final Function<char[], Boolean> complyFun;

    HandTypePart2(int strength, Function<char[], Boolean> complyFun) {
        this.strength = strength;
        this.complyFun = complyFun;
    }

    private static final List<HandTypePart2> HAND_TYPES_SORTED_BY_STRENGTH_REVERSED_PART_2 = Arrays.stream(HandTypePart2.values())
            .sorted(Comparator.comparingInt(HandTypePart2::getStrength).reversed())
            .toList();

    public static HandTypePart2 getHandTypeFromHand(Hand hand) {
        return HAND_TYPES_SORTED_BY_STRENGTH_REVERSED_PART_2.stream()
                .filter(handTypePart2 -> handTypePart2.complyFun.apply(hand.getCards()))
                .findFirst().orElse(HandTypePart2.HIGH_CARD);
    }
}
