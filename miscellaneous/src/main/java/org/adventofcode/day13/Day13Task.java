package org.adventofcode.day13;

import org.adventofcode.day12.Springs;

import java.math.BigInteger;
import java.util.Arrays;

import static org.adventofcode.Utils.getInput;
import static org.adventofcode.Utils.stringArrayToChars;
import static org.adventofcode.day13.MirrorFinder.calculateMirrorScore;
import static org.adventofcode.day13.MirrorFinder.calculateMirrorScorePart2;

public class Day13Task {

    public static void main(String[] args) {

        String[] input = getInput("day13_data.txt");

        long result1 = part1(input);
        long result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    private static long part1(String[] input) {
        long result = 0;

        for (int i = 0; i < input.length; i++) {
            int j = i;
            int start = i;
            while (j < input.length && !input[j].isEmpty()) {
                j++;
            }
            int end = j;
            String[] framesString = Arrays.copyOfRange(input, start, end);
            char[][] frames = stringArrayToChars(framesString);
            result += calculateMirrorScore(frames);
            i = j;
        }

        return result;
    }

    private static long part2(String[] input) {
        long result = 0;

        for (int i = 0; i < input.length; i++) {
            int j = i;
            int start = i;
            while (j < input.length && !input[j].isEmpty()) {
                j++;
            }
            int end = j;
            String[] framesString = Arrays.copyOfRange(input, start, end);
            char[][] frames = stringArrayToChars(framesString);
            result += calculateMirrorScorePart2(frames);
            i = j;
        }

        return result;
    }

}
