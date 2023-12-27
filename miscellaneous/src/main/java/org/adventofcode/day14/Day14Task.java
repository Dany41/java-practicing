package org.adventofcode.day14;

import org.adventofcode.Utils;

import static org.adventofcode.Utils.getInput;

public class Day14Task {

    public static void main(String[] args) {

        String[] input = getInput("day14_data.txt");

        long result1 = part1(input);
        long result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    private static long part1(String[] input) {
        char[][] chars = Utils.stringArrayToChars(input);
        ControlPanel controlPanel = new ControlPanel(chars);
        Tilter tilter = new Tilter(controlPanel);
        tilter.tilt(TiltDirection.NORTH);
        return controlPanel.calculatePressure();
    }

    private static long part2(String[] input) {
        long result = 0;

        return result;
    }

}
