package org.adventofcode.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ValuesHistory {


    List<List<Integer>> valueTree = new ArrayList<>();

    public ValuesHistory(String s) {
        List<Integer> firstNumbers = s.isEmpty()
            ? Collections.emptyList()
            : Arrays.stream(s.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        valueTree.add(firstNumbers);
        List<Integer> differences = calculateDifferenceList(firstNumbers);
        while (!differences.stream().allMatch(i -> i == 0)) {
            valueTree.add(differences);
            differences = calculateDifferenceList(differences);
        }
        valueTree = valueTree.stream().filter(Predicate.not(List::isEmpty)).toList();
    }

    public int predictNextValue() {
        if (valueTree.isEmpty()) return -1;
        int difference = valueTree.get(valueTree.size() - 1).get(0);
        valueTree.get(valueTree.size() - 1).add(difference);
        for (int i = valueTree.size() - 2; i >= 0; i--) {
            List<Integer> row = valueTree.get(i);
            Integer newValue = row.get(row.size() - 1) + difference;
            difference = newValue;
            row.add(newValue);
        }
        List<Integer> firstRow = valueTree.get(0);
        return firstRow.get(firstRow.size() - 1);
    }

    public int predictPreviousValue() {
        if (valueTree.isEmpty()) return -1;
        int difference = valueTree.get(valueTree.size() - 1).get(0);
        valueTree.get(valueTree.size() - 1).add(difference);
        for (int i = valueTree.size() - 2; i >= 0; i--) {
            List<Integer> row = valueTree.get(i);
            Integer newValue = row.get(0) - difference;
            difference = newValue;
            row.add(0, newValue);
        }
        return valueTree.get(0).get(0);
    }


    private List<Integer> calculateDifferenceList(List<Integer> list) {
        List<Integer> differences = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            differences.add(list.get(i + 1) - list.get(i));
        }
        return differences;
    }
}
