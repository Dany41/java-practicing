package org.adventofcode.day12;

import java.math.BigInteger;
import java.util.Arrays;

import static org.adventofcode.Utils.getInput;

public class Day12Task {

    public static void main(String[] args) {

        String[] input = getInput("day12_data.txt");

        BigInteger result1 = part1(input);
        BigInteger result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    private static BigInteger part1(String[] input) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < input.length; i++) {
            Springs springs = new Springs(input[i]);
            result = result.add(springs.arrangements());
        }
        return result;
    }

    private static BigInteger part2(String[] input) {
        return Arrays.stream(input)
                .parallel()
                .map(s -> {
                    String[] split = s.split(" ");
                    String contiguousGroup = split[1];
                    BigInteger baseArrangements = calculateArrangements(split[0], contiguousGroup, "?", 4);
                    System.out.println(s + " baseArrangements = " + baseArrangements);
                    return baseArrangements;
                }).reduce(BigInteger.ZERO, BigInteger::add);
    }

    private static BigInteger calculateArrangements(String springsGroup, String contiguousGroup, String engine, int repeat) {
        String springsString = repeatSpringsWithEngine(springsGroup, contiguousGroup, engine, repeat);
        Springs springs = new Springs(springsString);
        return springs.arrangements();
    }

    private static String repeatSpringsWithEngine(String springs, String contiguousGroup, String engine, int repeat) {
        StringBuilder sb = new StringBuilder(springs);
        for (int i = 0; i < repeat; i++) {
            sb.append(engine).append(springs);
        }
        sb.append(" ");
        sb.append(contiguousGroup);
        for (int i = 0; i < repeat; i++) {
            sb.append(",").append(contiguousGroup);
        }
        return sb.toString();
    }

    private static BigInteger calculate(String str, BigInteger base) {
        if (str.startsWith(".") && str.endsWith(".")) {
            return base.pow(2);
        }
        if (str.endsWith(".")) {
            return base;
        }
        int counter = 0;
        if (str.startsWith("#")) counter++;
        if (str.endsWith("#")) counter++;

        return base.subtract(BigInteger.valueOf(counter)).pow(2).divide(BigInteger.TWO);
    }

}


////                BigInteger.valueOf(
//                        Arrays.stream(input)
////                .parallel()
//                                .map(s -> {
//                                String[] split = s.split(" ");
//                                String springsString = split[0];
//                                String contiguousGroup = split[1];
////            if (springsString.startsWith("#") || springsString.endsWith("#")) {
//                                String springsStringWithOneMoreBrokenSpring = split[0] + "#" + split[0] + " " + contiguousGroup + "," + contiguousGroup;
//                                Springs springs = new Springs(springsString + " " + contiguousGroup);
//                                Springs springsWithOneMoreBrokenSpring = new Springs(springsStringWithOneMoreBrokenSpring);
//                                BigInteger arrangementsBase = BigInteger.valueOf(springs.arrangements());
//                                BigInteger arrangementsWithOneMoreBrokenSpring = BigInteger.valueOf(springsWithOneMoreBrokenSpring.arrangements());
////                BigInteger arrangementsWithOneMoreBrokenSpring = calculate(split[0], arrangementsBase);
//                                BigInteger arrangementsWithOneMoreOperationalSpring = arrangementsBase.pow(2);
//                                BigInteger arrangements = arrangementsWithOneMoreBrokenSpring.add(arrangementsWithOneMoreOperationalSpring).pow(4)
//                                .divide(arrangementsBase.pow(3));
//                                System.out.println(s + " arrangements = " + arrangements +
//                                " arrangementsBase = " + arrangementsBase +
//                                " arrangementsWithOneMoreOperationalSpring = " + arrangementsWithOneMoreOperationalSpring +
//                                " arrangementsWithOneMoreBrokenSpring = " + arrangementsWithOneMoreBrokenSpring);
//                                return arrangements;
////            } else {
////                String springsStringWithOneMoreSpringAtEnd = split[0] + "?" + " " + contiguousGroup;
////                String springsStringWithOneMoreSpringAtBeginning = "?" + split[0] + " " + contiguousGroup;
////                String springsStringWithOneMoreSprin = "?" + split[0] + "?" + " " + contiguousGroup;
////                Springs springs = new Springs(springsString + " " + contiguousGroup);
////                Springs springsWithOneMoreSpringAtEnd = new Springs(springsStringWithOneMoreSpringAtEnd);
////                Springs springsWithOneMoreSpringAtBeginning = new Springs(springsStringWithOneMoreSpringAtBeginning);
////                Springs springsWithOneMoreSpring = new Springs(springsStringWithOneMoreSprin);
////                long arrangementsWithOneMoreSpringAtEnd = springsWithOneMoreSpringAtEnd.arrangements();
////                long arrangementsWithOneMoreSpringAtBeginning = springsWithOneMoreSpringAtBeginning.arrangements();
////                long arrangementsWithOneMoreBrokenSpring = springsWithOneMoreSpring.arrangements();
////                BigInteger mult = BigInteger.valueOf(arrangementsWithOneMoreSpringAtEnd).pow(4)
////                        .multiply(BigInteger.valueOf(arrangementsWithOneMoreSpringAtBeginning).pow(4));
////                BigInteger arrangements = BigInteger.valueOf(springs.arrangements()).multiply(mult)
////                        .divide(BigInteger.valueOf(springs.arrangements()).pow(4));
////                System.out.println(s + " arrangements = " + arrangements + " arrangementsWithOneMoreSpringAtEnd = " +
////                        arrangementsWithOneMoreSpringAtEnd + " arrangementsWithOneMoreSpringAtBeginning = " +
////                        arrangementsWithOneMoreSpringAtBeginning + " arrangementsWithOneMoreBrokenSpring = " + arrangementsWithOneMoreBrokenSpring);
////                return arrangements;
////            }
////        }).reduce(0L, Long::sum)
//                                }).reduce(BigInteger.ZERO, BigInteger::add)
////                )
//                                ;