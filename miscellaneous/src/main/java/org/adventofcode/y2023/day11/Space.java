package org.adventofcode.y2023.day11;

import one.util.streamex.EntryStream;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Space {

    private final Long[][] map;

    public Space(String[] input) {
        this(input, 2);
    }

    public Space(String[] input, long expansionMultiplier) {
        Map<Integer, Pair<List<Character>, Boolean>> rowsToRepeating = new HashMap<>();
        Map<Integer, List<Character>> columnIndexToChars = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            Character[] row = input[i].chars().mapToObj(c -> (char) c).toArray(Character[]::new);
            rowsToRepeating.put(i, Pair.of(List.of(row), toRepeat(row)));

            for (int j = 0; j < row.length; j++) {
                columnIndexToChars.compute(j, (index, chars) -> {
                    if (chars == null) {
                        List<Character> res = new LinkedList<>();
                        res.add(row[index]);
                        return res;
                    } else {
                        chars.add(row[index]);
                        return chars;
                    }
                });
            }
        }
        Map<Integer, Boolean> columnsToRepeating = EntryStream.of(columnIndexToChars)
                .mapKeyValue((index, chars) -> Pair.of(index, toRepeat(chars.stream().toArray(Character[]::new))))
                .toMap(Pair::getKey, Pair::getValue);

        List<List<Long>> spaceMap = new LinkedList<>();



        EntryStream.of(rowsToRepeating)
                .forKeyValue((index, charsToRepeating) -> {
                    List<Long> newRow = new LinkedList<>();
                    if (Boolean.TRUE.equals(charsToRepeating.getValue())) {
                        spaceMap.add(charsToRepeating.getKey().stream().map(any -> expansionMultiplier).collect(Collectors.toList()));
                    } else {
                        newRow = charsToRepeating.getKey().stream().map(c -> c.equals('#') ? 0L : 1L).collect(Collectors.toList());
                        spaceMap.add(newRow);
                    }
                });

        for (int i = 0; i < spaceMap.size(); i++) {
            List<Long> row = spaceMap.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (columnsToRepeating.get(j)) row.set(j, row.get(j) * expansionMultiplier);
            }
        }

        this.map = spaceMap.stream()
                .map(chars -> chars.stream().toArray(Long[]::new))
                .toArray(Long[][]::new);
    }

    private Boolean toRepeat(Character[] array) {
        return Arrays.stream(array).noneMatch(c -> c.equals('#'));
    }

    public Long[][] getMap() {
        return this.map;
    }

    public List<Universe> getUniverses() {
        if (this.map.length == 0) return Collections.emptyList();
        List<Universe> universes = new LinkedList<>();
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[0].length; j++) {
                if (this.map[i][j].equals(0L)) universes.add(new Universe(j, i));
            }
        }
        return universes;
    }
}
