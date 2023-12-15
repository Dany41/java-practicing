package org.adventofcode.day9;

import static org.adventofcode.Utils.getInput;

public class Day9Task {

    public static void main(String[] args) {

        String[] input = getInput("day9_data.txt");

        int result1 = part1(input);
        int result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    private static int part1(String[] input) {
        int result = 0;
        for (String s : input) {
            ValuesHistory valuesHistory = new ValuesHistory(s);
            result += valuesHistory.predictNextValue();
        }
        return result;
    }

    private static int part2(String[] input) {
        int result = 0;
        for (String s : input) {
            ValuesHistory valuesHistory = new ValuesHistory(s);
            result += valuesHistory.predictPreviousValue();
        }
        return result;
    }

}
