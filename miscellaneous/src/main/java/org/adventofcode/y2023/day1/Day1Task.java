package org.adventofcode.y2023.day1;

import one.util.streamex.EntryStream;

import java.util.*;
import java.util.stream.Stream;

import static org.adventofcode.Utils.getInput;

public class Day1Task {

    private static Map<String, Integer> validObjects = new HashMap<>();

    static {
        validObjects.put("1", 1);
        validObjects.put("2", 2);
        validObjects.put("3", 3);
        validObjects.put("4", 4);
        validObjects.put("5", 5);
        validObjects.put("6", 6);
        validObjects.put("7", 7);
        validObjects.put("8", 8);
        validObjects.put("9", 9);
        validObjects.put("one", 1);
        validObjects.put("two", 2);
        validObjects.put("three", 3);
        validObjects.put("four", 4);
        validObjects.put("five", 5);
        validObjects.put("six", 6);
        validObjects.put("seven", 7);
        validObjects.put("eight", 8);
        validObjects.put("nine", 9);
    }

    public static void main(String[] args) {
        String[] input = getInput("day1_data.txt");

        int result1 = part1(input);
        int result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);
    }

    private static int part2(String[] input) {
        return Arrays.stream(input).map(str -> {
                    List<Integer> list = EntryStream.of(validObjects)
                            .filterKeys(str::contains)
                            .flatMapKeys(key -> Stream.of(str.indexOf(key), str.lastIndexOf(key)))
                            .sortedBy(Map.Entry::getKey)
                            .values()
                            .toList();
                    int firstDigit = list.isEmpty() ? 0 : list.get(0);
                    int lastDigit = list.isEmpty() ? 0 : list.get(list.size() - 1);
                    return firstDigit * 10 + lastDigit;
                })
                .reduce(Integer::sum).orElse(0);
    }

    private static int part1(String[] input) {
        int result = 0;
        for (String s : input) {
            int[] ints = s.chars().filter(Character::isDigit).map(Character::getNumericValue).toArray();
            int firstDigit = ints.length > 0 ? ints[0] : 0;
            int lastDigit = ints.length > 0 ? ints[ints.length - 1] : 0;
            result += firstDigit * 10 + lastDigit;
        }
        return result;
    }
}
