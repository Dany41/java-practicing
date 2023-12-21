package org.adventofcode.day11;

import one.util.streamex.StreamEx;

import static org.adventofcode.Utils.getInput;

public class Day11Task {

    public static void main(String[] args) {

        String[] input = getInput("day11_data.txt");

        long result1 = part1(input);
        long result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    private static long part1(String[] input) {
        Space space = new Space(input);
        Long[][] spaceMap = space.getMap();
        return StreamEx.ofPairs(space.getUniverses(), (u1, u2) -> {
                    long result = 0;
                    int fromX = Math.min(u1.x(), u2.x());
                    int toX = Math.max(u1.x(), u2.x());
                    int fromY = Math.min(u1.y(), u2.y());
                    int toY = Math.max(u1.y(), u2.y());
                    for (int i = fromX; i < toX; i++) {
                        result += spaceMap[fromY][i];
                        if (spaceMap[fromY][i].equals(0L)) result++;
                    }
                    for (int i = fromY; i < toY; i++) {
                        result += spaceMap[i][fromX];
                        if (spaceMap[i][fromX].equals(0L)) result++;
                    }
                    return result;
                })
                .reduce(0L, Long::sum);
    }

    private static long part2(String[] input) {
        Space space = new Space(input, 1000000);
        Long[][] spaceMap = space.getMap();
        return StreamEx.ofPairs(space.getUniverses(), (u1, u2) -> {
                    long result = 0;
                    int fromX = Math.min(u1.x(), u2.x());
                    int toX = Math.max(u1.x(), u2.x());
                    int fromY = Math.min(u1.y(), u2.y());
                    int toY = Math.max(u1.y(), u2.y());
                    for (int i = fromX; i < toX; i++) {
                        result += spaceMap[fromY][i];
                        if (spaceMap[fromY][i].equals(0L)) result++;
                    }
                    for (int i = fromY; i < toY; i++) {
                        result += spaceMap[i][fromX];
                        if (spaceMap[i][fromX].equals(0L)) result++;
                    }
                    return result;
                })
                .reduce(0L, Long::sum);
    }

}
