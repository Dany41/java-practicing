package org.adventofcode.y2023.day6;

import one.util.streamex.EntryStream;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.adventofcode.Utils.getInput;

public class Day6Task {

    public static void main(String[] args) {
        String[] input = getInput("day6_data.txt");

        int result1 = part1(input);
        long result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);
    }

    private static int part1(String[] input) {
        Integer[] times = Arrays.stream(input[0].substring(input[0].indexOf(":") + 1).split(" "))
                .filter(Predicate.not(String::isEmpty))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        Integer[] records = Arrays.stream(input[1].substring(input[1].indexOf(":") + 1).split(" "))
                .filter(Predicate.not(String::isEmpty))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        List<Pair<Integer, Integer>> timeToRecord = new ArrayList<>();

        for (int i = 0; i < times.length; i++) {
            timeToRecord.add(Pair.of(times[i], records[i]));
        }

        return timeToRecord.stream()
                .map(pair -> {
                    int time = pair.getLeft();
                    int highestScore = pair.getRight();
                    int result = 0;
                    for (int i = 1; i < time; i++) {
                        if (i * (time - i) > highestScore) result++;
                    }
                    return result;
                })
                .reduce(1, (i1, i2) -> i1 * i2);

    }

    private static long part2(String[] input) {
        BigInteger time = new BigInteger(input[0].substring(input[0].indexOf(":") + 1).chars()
                .filter(c -> !Character.isSpaceChar(c))
                .mapToObj(i -> Integer.toString(Character.digit(i, 10)))
                .reduce("", (str, s) -> str + s));

        BigInteger highestScore = new BigInteger(input[1].substring(input[1].indexOf(":") + 1).chars()
                .filter(c -> !Character.isSpaceChar(c))
                .mapToObj(i -> Integer.toString(Character.digit(i, 10)))
                .reduce("", (str, s) -> str + s));

        long result = 0;
        BigInteger limit = time.subtract(BigInteger.valueOf(14));
        for (BigInteger i = BigInteger.valueOf(14); i.compareTo(limit) < 1; i = i.add(BigInteger.ONE)) {
            if (i.multiply(time.subtract(i)).compareTo(highestScore) > 0) result++;
        }
        return result;
    }

}
