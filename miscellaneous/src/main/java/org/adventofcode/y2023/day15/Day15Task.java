package org.adventofcode.y2023.day15;

import one.util.streamex.EntryStream;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.adventofcode.Utils.getInput;

public class Day15Task {

    public static void main(String[] args) {

        String[] input = getInput("day15_data.txt");

        long result1 = part1(input);
        long result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    private static long part1(String[] input) {
        long result = 0;

        String[] data = input[0].split(",");

        for (int i = 0; i < data.length; i++) {
            long intermediate = 0;
            byte[] chars = data[i].getBytes(StandardCharsets.US_ASCII);
            for (int j = 0; j < chars.length; j++) {
                intermediate += chars[j];
                intermediate *= 17;
                intermediate %= 256;
            }
            result += intermediate;
        }

        return result;
    }

    private static long part2(String[] input) {
        long result = 0;

        String[] data = input[0].split(",");

        Map<Integer, List<Pair<String, Integer>>> map = IntStream.range(0, 256)
                .mapToObj(i -> i)
                .collect(Collectors.toMap(
                        i -> i,
                        i -> new ArrayList()
                ));

        for (int i = 0; i < data.length; i++) {
            long hash = 0;
            byte[] bytes = data[i].getBytes(StandardCharsets.US_ASCII);
            StringBuilder str = new StringBuilder();
            int j = 0;
            while (bytes[j] != 45 && bytes[j] != 61) {
                str.append(data[i].charAt(j));
                hash += bytes[j];
                hash *= 17;
                hash %= 256;
                j++;
            }
            if (bytes[j] == 45) {
                List<Pair<String, Integer>> pairs = map.get((int) hash);
                boolean containsHash = pairs.stream().anyMatch(p -> p.getLeft().contentEquals(str));
                if (containsHash) {
                    Pair<String, Integer> toReplace = pairs.stream().filter(p -> p.getLeft().contentEquals(str)).findFirst().get();
                    pairs.remove(toReplace);
                }
            } else {
                j++;
                map.merge((int) hash, List.of(Pair.of(str.toString(), Character.digit(data[i].charAt(j), 10))), (list1, list2) -> {
                    boolean containsHash = list1.stream().anyMatch(p -> p.getLeft().contentEquals(str));
                    if (containsHash) {
                        Pair<String, Integer> toReplace = list1.stream().filter(p -> p.getLeft().contentEquals(str)).findFirst().get();
                        int indexOfReplaced = list1.indexOf(toReplace);
                        list1.add(indexOfReplaced, list2.get(0));
                        list1.remove(toReplace);
                    } else {
                        list1.addAll(list2);
                    }
                    return list1;
                });
            }
        }

        return EntryStream.of(map)
                .flatMapKeyValue((boxIndex, strToValList) ->
                        EntryStream.of(strToValList).mapKeyValue((index, strToVal) -> (boxIndex + 1) * (index + 1) * strToVal.getRight())).mapToLong(i -> (long) i).sum();
    }

}
