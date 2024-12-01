package org.adventofcode.y2023.day7;

import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum HandTypePart1 {

    FIVE_OF_A_KIND(7, chars -> charsHaveExactAmountOfInputChar(chars, chars[0], 5)),
    FOUR_OF_A_KIND(6, chars -> {
        Set<Character> uniqueChars = uniqueChars(chars);
        Set<Integer> result = uniqueChars.stream().map(c -> countCharInChars(chars, c)).collect(Collectors.toSet());
        return result.contains(4) && result.contains(1);
    }),
    FULL_HOUSE(5, chars -> {
        Set<Character> uniqueChars = uniqueChars(chars);
        Set<Integer> result = uniqueChars.stream().map(c -> countCharInChars(chars, c)).collect(Collectors.toSet());
        return result.contains(2) && result.contains(3);
    }),
    THREE_OF_A_KIND(4, chars -> {
        Set<Character> uniqueChars = uniqueChars(chars);
        Set<Integer> result = uniqueChars.stream().map(c -> countCharInChars(chars, c)).collect(Collectors.toSet());
        return result.contains(1) && result.contains(3);
    }),
    TWO_PAIR(3, chars -> {
        Set<Character> uniqueChars = uniqueChars(chars);
        List<Integer> result = uniqueChars.stream().map(c -> countCharInChars(chars, c)).toList();
        return result.stream().filter(i -> i == 2).count() == 2;
    }),
    ONE_PAIR(2, chars -> {
        Set<Character> uniqueChars = uniqueChars(chars);
        List<Integer> result = uniqueChars.stream().map(c -> countCharInChars(chars, c)).toList();
        int max = result.stream().max(Integer::compare).orElse(0);
        return result.stream().filter(i -> i == 2).count() == 1 && max == 2;
    }),
    HIGH_CARD(1, chars -> {
        Set<Character> uniqueChars = uniqueChars(chars);
        List<Integer> result = uniqueChars.stream().map(c -> countCharInChars(chars, c)).toList();
        return result.stream().allMatch(i -> i == 1);
    });

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

    HandTypePart1(int strength, Function<char[], Boolean> complyFun) {
        this.strength = strength;
        this.complyFun = complyFun;
    }

    private static final List<HandTypePart1> HAND_TYPES_SORTED_BY_STRENGTH_REVERSED_PART_1 = Arrays.stream(HandTypePart1.values())
            .sorted(Comparator.comparingInt(HandTypePart1::getStrength).reversed())
            .toList();

    public static HandTypePart1 getHandTypeFromHand(Hand hand) {
        return HAND_TYPES_SORTED_BY_STRENGTH_REVERSED_PART_1.stream()
                .filter(handTypePart1 -> handTypePart1.complyFun.apply(hand.getCards()))
                .findFirst().orElse(HandTypePart1.HIGH_CARD);
    }
}
