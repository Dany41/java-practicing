package org.adventofcode.y2024.day1;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.adventofcode.Utils.getInput;

public class Day1Task {

    public static void main(String[] args) {
        String[] input = getInput("aoc/2024/day1_data.txt");

        long result1 = part1(input);
        long result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);
    }

    private static long part1(String[] input) {
        long[] left = new long[input.length];
        long[] right = new long[input.length];

        for (int i = 0; i < input.length; i++) {
            String[] intsAsStrings = input[i].split("   ");
            left[i] = Integer.parseInt(intsAsStrings[0]);
            right[i] = Integer.parseInt(intsAsStrings[1]);
        }

        Arrays.sort(left);
        Arrays.sort(right);

        long res = 0;
        for (int i = 0; i < input.length; i++) {
            res += Math.abs(left[i] - right[i]);
        }
        return res;
    }

    private static long part2(String[] input) {
        long[] left = new long[input.length];
        long[] right = new long[input.length];

        for (int i = 0; i < input.length; i++) {
            String[] intsAsStrings = input[i].split("   ");
            left[i] = Integer.parseInt(intsAsStrings[0]);
            right[i] = Integer.parseInt(intsAsStrings[1]);
        }

        Map<Long, Long> rightCollected = Arrays.stream(right)
                .boxed()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        long res = 0;
        for (int i = 0; i < input.length; i++) {
            res += left[i] * rightCollected.getOrDefault(left[i], 0L);
        }
        return res;
    }

}
