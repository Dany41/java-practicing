package org.adventofcode.day12;

import lombok.Getter;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

public class Springs {

    char[] springs;
    int[] contiguousGroup;

//    @Getter(lazy = true)
//    private final long damagedCount = computeDamagedCount();
//
//    private long computeDamagedCount() {
//        return Objects.requireNonNull(springs).stream().filter(c -> c.equals('#')).count();
//    }

//    @Getter(lazy = true)
//    private final long contiguousGroupDamagedCount = computeContiguousGroupDamagedCount();
//
//    private int computeContiguousGroupDamagedCount() {
//        return Objects.requireNonNull(contiguousGroup).stream().reduce(0, Integer::sum);
//    }

    public Springs(String s) {
        try {
            String[] split = s.split(" ");
            char[] chars = split[0].toCharArray();
            List<Character> intermediate = new ArrayList<>();
            for (char aChar : chars) {
                if (aChar != '.' || intermediate.isEmpty() || !(intermediate.get(intermediate.size() - 1).equals('.'))) {
                    intermediate.add(aChar);
                }
            }
            this.springs = new char[intermediate.size()];
            for (int i = 0; i < intermediate.size(); i++) {
                this.springs[i] = intermediate.get(i);
            }
            List<Integer> intermediateInts = Arrays.stream(split[1].split(",")).map(Integer::parseInt).toList();
            this.contiguousGroup = new int[intermediateInts.size()];
            for (int i = 0; i < intermediateInts.size(); i++) {
                this.contiguousGroup[i] = intermediateInts.get(i);
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Provided input cannot be converted into Springs object");
        }
    }

    public Springs(char[] springs, int[] contiguousGroup) {
        this.springs = springs;
        this.contiguousGroup = contiguousGroup;
    }

    public BigInteger arrangements() {
        switch (this.validity()) {
            case VALID -> {
                return BigInteger.ONE;
            }
            case INVALID -> {
                return BigInteger.ZERO;
            }
            case UNDEFINED -> {
                int index = -1;
                for (int i = 0; i < springs.length; i++) {
                    if (springs[i] == '?') {
                        index = i;
                        break;
                    }
                }
                char[] copy = Arrays.copyOf(this.springs, this.springs.length);
                this.springs[index] = '.';
                BigInteger springsWithOperationalArrangements = arrangements();

//                List<Character> newSpringWithDamaged = new ArrayList<>(springs);

                this.springs = Arrays.copyOf(copy, copy.length);
                this.springs[index] = '#';
                BigInteger springsWithDamagedArrangements = arrangements();

//                Springs springsWithOperational = new Springs(newSpringWithOperational, this.contiguousGroup);
//                Springs springsWithDamaged = new Springs(newSpringWithDamaged, this.contiguousGroup);



                return springsWithOperationalArrangements.add(springsWithDamagedArrangements);
            }
        }
        return BigInteger.valueOf(-1);
    }

    public Validity validity() {

        if (springs[0] == '?') return Validity.UNDEFINED;
        int counter = 0;
        if (springs[0] == '#') counter++;
        int pointer = 0;
        int j = 1;
        while (pointer < contiguousGroup.length && j < springs.length) {
            if (contiguousGroup[pointer] < counter) return Validity.INVALID;
            switch (springs[j]) {
                case '?' -> {
                    return Validity.UNDEFINED;
                }
                case '.' -> {
                    if (counter != 0) {
                        if (contiguousGroup[pointer] != counter) return Validity.INVALID;
                        pointer++;
                        counter = 0;
                    }
                }
                case '#' -> counter++;
            }
            j++;
        }
        if (counter != 0) {
            if (contiguousGroup[pointer] != counter) return Validity.INVALID;
            pointer++;
        }
        for (int i = j; i < springs.length; i++) {
            if (springs[i] == '#') return Validity.INVALID;
        }
        return pointer == contiguousGroup.length ? Validity.VALID : Validity.INVALID;
    }

    enum Validity {
        VALID, INVALID, UNDEFINED
    }
}
