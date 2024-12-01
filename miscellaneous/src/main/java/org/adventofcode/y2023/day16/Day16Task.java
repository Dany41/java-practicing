package org.adventofcode.y2023.day16;

import org.adventofcode.Utils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

import static org.adventofcode.Utils.getInput;

public class Day16Task {
    public static void main(String[] args) {

        String[] input = getInput("day16_example_data.txt");

        long result1 = part1(input);
        long result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    private static long part1(String[] input) {
        long result = 0;

        char[][] grid = Utils.stringArrayToChars(input);

        int x = 0;
        int y = 0;

        Set<Pair<Integer, Integer>> entered = new HashSet<>();

        BeamHeading heading = BeamHeading.RIGHT;

        do {
            switch (grid[y][x]) {
                case '.' -> {
                    entered.add(Pair.of(x, y));
                    Pair<Integer, Integer> pair = moveForward(heading, x, y);
                    x = pair.getLeft();
                    y = pair.getRight();
                }
                case '\\' -> {
                    heading = switch (heading) {
                        case RIGHT -> BeamHeading.BOTTOM;
                        case BOTTOM -> BeamHeading.RIGHT;
                        case LEFT -> BeamHeading.TOP;
                        case TOP -> BeamHeading.LEFT;
                    };
                    entered.add(Pair.of(x, y));
                    Pair<Integer, Integer> pair = moveForward(heading, x, y);
                    x = pair.getLeft();
                    y = pair.getRight();
                }
                case '/' -> {
                    heading = switch (heading) {
                        case RIGHT -> BeamHeading.TOP;
                        case TOP -> BeamHeading.RIGHT;
                        case LEFT -> BeamHeading.BOTTOM;
                        case BOTTOM -> BeamHeading.LEFT;
                    };
                    entered.add(Pair.of(x, y));
                    Pair<Integer, Integer> pair = moveForward(heading, x, y);
                    x = pair.getLeft();
                    y = pair.getRight();
                }
                case '-' -> {
                    heading = switch (heading) {
                        case RIGHT -> BeamHeading.RIGHT;
                        case LEFT -> BeamHeading.LEFT;
                        case TOP -> BeamHeading.RIGHT;
                        case BOTTOM -> BeamHeading.LEFT;
                    };
                    entered.add(Pair.of(x, y));
                    Pair<Integer, Integer> pair = moveForward(heading, x, y);
                    x = pair.getLeft();
                    y = pair.getRight();
                }
            }
        } while (1 == 2);

        return result;
    }

    private static Pair<Integer, Integer> moveForward(BeamHeading beamHeading, int x, int y) {
        switch (beamHeading) {
            case RIGHT -> {
                return Pair.of(x + 1, y);
            }
            case BOTTOM -> {
                return Pair.of(x, y + 1);
            }
            case LEFT -> {
                return Pair.of(x - 1, y);
            }
            case TOP -> {
                return Pair.of(x, y - 1);
            }
        }
        return Pair.of(x, y);
    }

    private static long part2(String[] input) {
        long result = 0;

        return result;
    }
}
