package org.adventofcode.day5;

import one.util.streamex.EntryStream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.adventofcode.Utils.getInput;

public class Day5Task {

    public static void main(String[] args) {
        String[] input = getInput("day5_data.txt");

        long result1 = part1(input);
        long result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);
    }

    private static long part1(String[] input) {
        Set<Long> seeds = Arrays.stream(input[0].substring(input[0].indexOf(": ") + 2).split(" "))
                .map(Long::parseLong).collect(Collectors.toSet());
        Day5InputContent day5InputContent = new Day5InputContent(input);
        return seeds.stream()
                .map(seed -> getValueFromRangedMap(day5InputContent.seedToSoil, seed))
                .map(soil -> getValueFromRangedMap(day5InputContent.soilToFertilizer, soil))
                .map(fertilizer -> getValueFromRangedMap(day5InputContent.fertilizerToWater, fertilizer))
                .map(water -> getValueFromRangedMap(day5InputContent.waterToLight, water))
                .map(light -> getValueFromRangedMap(day5InputContent.lightToTemperature, light))
                .map(temperature -> getValueFromRangedMap(day5InputContent.temperatureToHumidity, temperature))
                .map(humidity -> getValueFromRangedMap(day5InputContent.humidityToLocation, humidity))
                .min(Long::compareTo).orElse(0L);
    }

    private static long part2(String[] input) {

        long result = Long.MAX_VALUE;
        String[] seedRawInfo = input[0].substring(input[0].indexOf(": ") + 2).split(" ");
        Day5InputContent day5InputContent = new Day5InputContent(input);
        for (int i = 0; i < seedRawInfo.length; i += 2) { // seedRawInfo.length
            long start = Long.parseLong(seedRawInfo[i]);
            long length = Long.parseLong(seedRawInfo[i + 1]);
            SimpleRange simpleRange = new SimpleRange(start, start + length - 1);
            long intermediateResult = Stream.of(simpleRange)
                    .flatMap(seed -> mergeRangedMaps(seed, day5InputContent.seedToSoil).stream())
                    .flatMap(soil -> mergeRangedMaps(soil, day5InputContent.soilToFertilizer).stream())
                    .flatMap(fertilizer -> mergeRangedMaps(fertilizer, day5InputContent.fertilizerToWater).stream())
                    .flatMap(water -> mergeRangedMaps(water, day5InputContent.waterToLight).stream())
                    .flatMap(light -> mergeRangedMaps(light, day5InputContent.lightToTemperature).stream())
                    .flatMap(temperature -> mergeRangedMaps(temperature, day5InputContent.temperatureToHumidity).stream())
                    .flatMap(humidity -> mergeRangedMaps(humidity, day5InputContent.humidityToLocation).stream())
                    .map(SimpleRange::start)
                    .min(Long::compareTo).orElse(0L);
            result = Math.min(result, intermediateResult);
        }
        return result;
    }

    private static long getValueFromRangedMap(Map<SimpleRange, SimpleRange> rangedMap, long value) {
        return EntryStream.of(rangedMap)
                .filterKeys(keyRange -> keyRange.contains(value))
                .mapKeyValue((keyRange, valueRange) -> {
                    long delta = value - keyRange.start();
                    return valueRange.start() + delta;
                })
                .findFirst().orElse(value);
    }

    private static List<SimpleRange> mergeRangedMaps(SimpleRange inputSimpleRange, Map<SimpleRange, SimpleRange> intermediateRangedMap) {
        long start = inputSimpleRange.start();
        long end = inputSimpleRange.end();
        Set<Long> points = intermediateRangedMap.keySet().stream().flatMap(range -> Stream.of(range.start(), range.end())).collect(Collectors.toSet());

        List<Long> validPoints = points.stream().filter(point -> point >= start && point <= end).collect(Collectors.toList());
        validPoints.add(start);
        validPoints.add(end);
        validPoints.sort(Long::compare);

        List<SimpleRange> result = new ArrayList<>();

        for (int i = 0; i < validPoints.size() - 1; i++) {
            SimpleRange simpleRange = new SimpleRange(validPoints.get(i), validPoints.get(i + 1));
            Map<SimpleRange, SimpleRange> map = EntryStream.of(intermediateRangedMap)
                    .filterKeys(keyRange -> keyRange.contains(simpleRange))
                    .toMap();
            if (map.isEmpty()) {
                long start1 = validPoints.get(i) == start ? start : validPoints.get(i) + 1;
                long end1 = validPoints.get(i + 1) == end ? end : validPoints.get(i + 1) - 1;
                result.add(new SimpleRange(start1, end1));
            } else {
                EntryStream.of(map)
                        .forKeyValue((keyRange, keyValue) -> {
                            long delta = simpleRange.start() - keyRange.start();
                            SimpleRange adjustedRange = new SimpleRange(keyValue.start() + delta, keyValue.start() + delta + simpleRange.length() - 1);
                            result.add(adjustedRange.intersection(keyValue));
                        });
            }
        }

        return result;
    }

}

class Day5InputContent {

    private static final int ROWS_BETWEEN_BLOCKS = 2;
    private String[] rawData;
    public Map<SimpleRange, SimpleRange> seedToSoil = new HashMap<>();
    public Map<SimpleRange, SimpleRange> soilToFertilizer = new HashMap<>();
    public Map<SimpleRange, SimpleRange> fertilizerToWater = new HashMap<>();
    public Map<SimpleRange, SimpleRange> waterToLight = new HashMap<>();
    public Map<SimpleRange, SimpleRange> lightToTemperature = new HashMap<>();
    public Map<SimpleRange, SimpleRange> temperatureToHumidity = new HashMap<>();
    public Map<SimpleRange, SimpleRange> humidityToLocation = new HashMap<>();

    public Day5InputContent(String[] input) {
        rawData = input;
        int pointer = 3;

        pointer = fillInMap(seedToSoil, pointer);
        pointer = fillInMap(soilToFertilizer, pointer);
        pointer = fillInMap(fertilizerToWater, pointer);
        pointer = fillInMap(waterToLight, pointer);
        pointer = fillInMap(lightToTemperature, pointer);
        pointer = fillInMap(temperatureToHumidity, pointer);
        fillInMap(humidityToLocation, pointer);
    }

    private int fillInMap(Map<SimpleRange, SimpleRange> seedToSoil, int pointer) {
        while (pointer < rawData.length && !rawData[pointer].isEmpty()) {
            Long[] rowInfo = Arrays.stream(rawData[pointer].split(" ")).map(Long::parseLong).toArray(Long[]::new);
            Triple row = new Triple(rowInfo[0], rowInfo[1], rowInfo[2]);
            seedToSoil.put(
                    new SimpleRange(row.sourceRange(), row.sourceRange() + row.rangeLength() - 1),
                    new SimpleRange(row.destRange(), row.destRange() + row.rangeLength() - 1));
            pointer++;
        }
        return pointer + ROWS_BETWEEN_BLOCKS;
    }

}

record Triple(long destRange, long sourceRange, long rangeLength) {
}

record SimpleRange(long start, long end) {
    public boolean contains(long value) {
        return value >= start && value <= end;
    }

    public long length() {
        return end - start + 1;
    }

    public boolean contains(SimpleRange otherRange) {
        return otherRange.start >= start && otherRange.end <= end;
    }

    public boolean intersects(SimpleRange otherRange) {
        return this.contains(otherRange.start) || this.contains(otherRange.end);
    }

    public SimpleRange intersection(SimpleRange otherRange) {
        long start = Math.max(this.start, otherRange.start);
        long end = Math.min(this.end, otherRange.end);
        return new SimpleRange(start, end);
    }

    public List<SimpleRange> without(SimpleRange otherRange) {
        if (otherRange.contains(this)) {
            return List.of(this);
        }
        if (this.contains(otherRange)) {
            return List.of(new SimpleRange(this.start, otherRange.start), new SimpleRange(otherRange.end, this.end));
        }
        if (this.intersects(otherRange)) {
            if (this.contains(otherRange.start)) {
                return List.of(new SimpleRange(this.start, otherRange.start));
            }
            if (this.contains(otherRange.end)) {
                return List.of(new SimpleRange(otherRange.end, this.end));
            }
        }
        return Collections.emptyList();
    }
}
