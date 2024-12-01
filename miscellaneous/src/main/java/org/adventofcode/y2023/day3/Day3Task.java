package org.adventofcode.y2023.day3;

import java.util.ArrayList;
import java.util.List;

import static org.adventofcode.Utils.getInput;

public class Day3Task {

    public static void main(String[] args) {

        // max is 12 red cubes, 13 green cubes, and 14 blue cubes
        String[] input = getInput("day3_data.txt");

        int result1 = part1(input);
        int result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    private static int part1(String[] input) {

        // the first line processing
        List<Triple> numbersOnLine = new ArrayList<>();
        List<Integer> validNumbers = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        char[] firstLine = input[0].toCharArray();
        for (int i = 0; i < firstLine.length; i++) {
            if (Character.isDigit(firstLine[i])) {
                number.append(firstLine[i]);
            }
            if (i < firstLine.length - 1 && !number.isEmpty() && !Character.isDigit(firstLine[i + 1])) {
                numbersOnLine.add(new Triple(i - number.length() + 1, i, Integer.parseInt(number.toString())));
                number = new StringBuilder();
            }
        }
        if (!number.isEmpty()) numbersOnLine.add(new Triple(firstLine.length - number.length(), firstLine.length - 1, Integer.parseInt(number.toString())));

        for (int j = 0; j < numbersOnLine.size(); j++) {
            Triple triple = numbersOnLine.get(j);
            boolean isValid = false;
            boolean isNotInTheBeginning = triple.fromIndex() > 0;
            boolean isNotAtTheEnd = triple.toIndex() + 2 < input[0].length();
            int sliceFromIndex = isNotInTheBeginning ? triple.fromIndex() - 1 : triple.fromIndex();
            int sliceToIndex = isNotAtTheEnd ? triple.toIndex() + 2 : triple.toIndex() + 1;

            char[] nextLineSymbols = input[1].substring(sliceFromIndex, sliceToIndex).toCharArray();

            for (int k = 0; k < nextLineSymbols.length; k++) {
                if (nextLineSymbols[k] != '.') {
                    isValid = true;
                    break;
                }
            }

            if (isValid) {
                validNumbers.add(triple.value());
                continue;
            }

            char prevSymbolOnCurrentLine = isNotInTheBeginning ? input[0].charAt(triple.fromIndex() - 1) : '.';

            if (prevSymbolOnCurrentLine != '.') {
                validNumbers.add(triple.value());
                continue;
            }

            char nextSymbolOnCurrentLine = isNotAtTheEnd ? input[0].charAt(triple.toIndex() + 2) : '.';

            if (nextSymbolOnCurrentLine != '.') {
                validNumbers.add(triple.value());
            }
        }



        // processing all lines except first and last
        numbersOnLine = new ArrayList<>();
        number = new StringBuilder();

        for (int i = 1; i < input.length - 1; i++) {
            numbersOnLine = new ArrayList<>();
            char[] charArray = input[i].toCharArray();
            number = new StringBuilder();
            for (int j = 0; j < charArray.length; j++) {
                if (Character.isDigit(charArray[j])) {
                    number.append(charArray[j]);
                }
                if (j < charArray.length - 1 && !number.isEmpty() && !Character.isDigit(charArray[j + 1])) {
                    numbersOnLine.add(new Triple(j - number.length() + 1, j, Integer.parseInt(number.toString())));
                    number = new StringBuilder();
                }
            }
            if (!number.isEmpty()) numbersOnLine.add(new Triple(charArray.length - number.length(), charArray.length - 1, Integer.parseInt(number.toString())));
            for (int j = 0; j < numbersOnLine.size(); j++) {
                Triple triple = numbersOnLine.get(j);
                boolean isValid = false;
                boolean isNotInTheBeginning = triple.fromIndex() > 0;
                boolean isNotAtTheEnd = triple.toIndex() + 2 < input[i].length();
                int sliceFromIndex = isNotInTheBeginning ? triple.fromIndex() - 1 : triple.fromIndex();
                int sliceToIndex = isNotAtTheEnd ? triple.toIndex() + 2 : triple.toIndex() + 1;
                char[] previousLineSymbols = input[i - 1].substring(sliceFromIndex, sliceToIndex).toCharArray();

                for (int k = 0; k < previousLineSymbols.length; k++) {
                    if (previousLineSymbols[k] != '.') {
                        isValid = true;
                        break;
                    }
                }

                if (isValid) {
                    validNumbers.add(triple.value());
                    continue;
                }

                char[] nextLineSymbols = input[i + 1].substring(sliceFromIndex, sliceToIndex).toCharArray();

                for (int k = 0; k < nextLineSymbols.length; k++) {
                    if (nextLineSymbols[k] != '.') {
                        isValid = true;
                        break;
                    }
                }

                if (isValid) {
                    validNumbers.add(triple.value());
                    continue;
                }

                char prevSymbolOnCurrentLine = isNotInTheBeginning ? input[i].charAt(triple.fromIndex() - 1) : '.';

                if (prevSymbolOnCurrentLine != '.') {
                    validNumbers.add(triple.value());
                    continue;
                }

                char nextSymbolOnCurrentLine = isNotAtTheEnd ? input[i].charAt(triple.toIndex() + 1) : '.';

                if (nextSymbolOnCurrentLine != '.') {
                    validNumbers.add(triple.value());
                }
            }
        }

        // the last line processing
        numbersOnLine = new ArrayList<>();
        number = new StringBuilder();
        char[] lastLine = input[input.length - 1].toCharArray();
        for (int i = 0; i < lastLine.length; i++) {
            if (Character.isDigit(lastLine[i])) {
                number.append(lastLine[i]);
            }
            if (i < lastLine.length - 1 && !number.isEmpty() && !Character.isDigit(lastLine[i + 1])) {
                numbersOnLine.add(new Triple(i - number.length() + 1, i, Integer.parseInt(number.toString())));
                number = new StringBuilder();
            }
        }
        if (!number.isEmpty()) numbersOnLine.add(new Triple(lastLine.length - number.length(), lastLine.length - 1, Integer.parseInt(number.toString())));

        for (int j = 0; j < numbersOnLine.size(); j++) {
            Triple triple = numbersOnLine.get(j);
            boolean isValid = false;
            boolean isNotInTheBeginning = triple.fromIndex() > 0;
            boolean isNotAtTheEnd = triple.toIndex() + 2 < input[input.length - 1].length();
            int sliceFromIndex = isNotInTheBeginning ? triple.fromIndex() - 1 : triple.fromIndex();
            int sliceToIndex = isNotAtTheEnd ? triple.toIndex() + 2 : triple.toIndex() + 1;

            char[] previousLineSymbols = input[input.length - 2].substring(sliceFromIndex, sliceToIndex).toCharArray();

            for (int k = 0; k < previousLineSymbols.length; k++) {
                if (previousLineSymbols[k] != '.') {
                    isValid = true;
                    break;
                }
            }

            if (isValid) {
                validNumbers.add(triple.value());
                continue;
            }

            char prevSymbolOnCurrentLine = isNotInTheBeginning ? input[input.length - 1].charAt(triple.fromIndex() - 1) : '.';

            if (prevSymbolOnCurrentLine != '.') {
                validNumbers.add(triple.value());
                continue;
            }

            char nextSymbolOnCurrentLine = isNotAtTheEnd ? input[input.length - 1].charAt(triple.toIndex() + 2) : '.';

            if (nextSymbolOnCurrentLine != '.') {
                validNumbers.add(triple.value());
            }
        }
        return validNumbers.stream().mapToInt(i -> i).sum();
    }

    private static int part2(String[] input) {
        // the first line processing
        List<Quad> numbersOnLine = new ArrayList<>();
        List<Quad> validNumbers = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        char[] firstLine = input[0].toCharArray();
        for (int i = 0; i < firstLine.length; i++) {
            if (Character.isDigit(firstLine[i])) {
                number.append(firstLine[i]);
            }
            if (i < firstLine.length - 1 && !number.isEmpty() && !Character.isDigit(firstLine[i + 1])) {
                numbersOnLine.add(new Quad(0, i - number.length() + 1, i, Integer.parseInt(number.toString())));
                number = new StringBuilder();
            }
        }
        if (!number.isEmpty()) numbersOnLine.add(new Quad(0,firstLine.length - number.length(), firstLine.length - 1, Integer.parseInt(number.toString())));

        for (int j = 0; j < numbersOnLine.size(); j++) {
            Quad quad = numbersOnLine.get(j);
            boolean isValid = false;
            boolean isNotInTheBeginning = quad.fromIndex() > 0;
            boolean isNotAtTheEnd = quad.toIndex() + 2 < input[0].length();
            int sliceFromIndex = isNotInTheBeginning ? quad.fromIndex() - 1 : quad.fromIndex();
            int sliceToIndex = isNotAtTheEnd ? quad.toIndex() + 2 : quad.toIndex() + 1;

            char[] nextLineSymbols = input[1].substring(sliceFromIndex, sliceToIndex).toCharArray();

            for (int k = 0; k < nextLineSymbols.length; k++) {
                if (nextLineSymbols[k] != '.') {
                    isValid = true;
                    break;
                }
            }

            if (isValid) {
                validNumbers.add(quad);
                continue;
            }

            char prevSymbolOnCurrentLine = isNotInTheBeginning ? input[0].charAt(quad.fromIndex() - 1) : '.';

            if (prevSymbolOnCurrentLine != '.') {
                validNumbers.add(quad);
                continue;
            }

            char nextSymbolOnCurrentLine = isNotAtTheEnd ? input[0].charAt(quad.toIndex() + 2) : '.';

            if (nextSymbolOnCurrentLine != '.') {
                validNumbers.add(quad);
            }
        }



        // processing all lines except first and last
        numbersOnLine = new ArrayList<>();
        number = new StringBuilder();

        for (int i = 1; i < input.length - 1; i++) {
            numbersOnLine = new ArrayList<>();
            char[] charArray = input[i].toCharArray();
            number = new StringBuilder();
            for (int j = 0; j < charArray.length; j++) {
                if (Character.isDigit(charArray[j])) {
                    number.append(charArray[j]);
                }
                if (j < charArray.length - 1 && !number.isEmpty() && !Character.isDigit(charArray[j + 1])) {
                    numbersOnLine.add(new Quad(i,j - number.length() + 1, j, Integer.parseInt(number.toString())));
                    number = new StringBuilder();
                }
            }
            if (!number.isEmpty()) numbersOnLine.add(new Quad(i,charArray.length - number.length(), charArray.length - 1, Integer.parseInt(number.toString())));
            for (int j = 0; j < numbersOnLine.size(); j++) {
                Quad quad = numbersOnLine.get(j);
                boolean isValid = false;
                boolean isNotInTheBeginning = quad.fromIndex() > 0;
                boolean isNotAtTheEnd = quad.toIndex() + 2 < input[i].length();
                int sliceFromIndex = isNotInTheBeginning ? quad.fromIndex() - 1 : quad.fromIndex();
                int sliceToIndex = isNotAtTheEnd ? quad.toIndex() + 2 : quad.toIndex() + 1;
                char[] previousLineSymbols = input[i - 1].substring(sliceFromIndex, sliceToIndex).toCharArray();

                for (int k = 0; k < previousLineSymbols.length; k++) {
                    if (previousLineSymbols[k] != '.') {
                        isValid = true;
                        break;
                    }
                }

                if (isValid) {
                    validNumbers.add(quad);
                    continue;
                }

                char[] nextLineSymbols = input[i + 1].substring(sliceFromIndex, sliceToIndex).toCharArray();

                for (int k = 0; k < nextLineSymbols.length; k++) {
                    if (nextLineSymbols[k] != '.') {
                        isValid = true;
                        break;
                    }
                }

                if (isValid) {
                    validNumbers.add(quad);
                    continue;
                }

                char prevSymbolOnCurrentLine = isNotInTheBeginning ? input[i].charAt(quad.fromIndex() - 1) : '.';

                if (prevSymbolOnCurrentLine != '.') {
                    validNumbers.add(quad);
                    continue;
                }

                char nextSymbolOnCurrentLine = isNotAtTheEnd ? input[i].charAt(quad.toIndex() + 1) : '.';

                if (nextSymbolOnCurrentLine != '.') {
                    validNumbers.add(quad);
                }
            }
        }

        // the last line processing
        numbersOnLine = new ArrayList<>();
        number = new StringBuilder();
        char[] lastLine = input[input.length - 1].toCharArray();
        for (int i = 0; i < lastLine.length; i++) {
            if (Character.isDigit(lastLine[i])) {
                number.append(lastLine[i]);
            }
            if (i < lastLine.length - 1 && !number.isEmpty() && !Character.isDigit(lastLine[i + 1])) {
                numbersOnLine.add(new Quad(input.length - 1,i - number.length() + 1, i, Integer.parseInt(number.toString())));
                number = new StringBuilder();
            }
        }
        if (!number.isEmpty()) numbersOnLine.add(new Quad(input.length - 1, lastLine.length - number.length(), lastLine.length - 1, Integer.parseInt(number.toString())));

        for (int j = 0; j < numbersOnLine.size(); j++) {
            Quad quad = numbersOnLine.get(j);
            boolean isValid = false;
            boolean isNotInTheBeginning = quad.fromIndex() > 0;
            boolean isNotAtTheEnd = quad.toIndex() + 2 < input[input.length - 1].length();
            int sliceFromIndex = isNotInTheBeginning ? quad.fromIndex() - 1 : quad.fromIndex();
            int sliceToIndex = isNotAtTheEnd ? quad.toIndex() + 2 : quad.toIndex() + 1;

            char[] previousLineSymbols = input[input.length - 2].substring(sliceFromIndex, sliceToIndex).toCharArray();

            for (int k = 0; k < previousLineSymbols.length; k++) {
                if (previousLineSymbols[k] != '.') {
                    isValid = true;
                    break;
                }
            }

            if (isValid) {
                validNumbers.add(quad);
                continue;
            }

            char prevSymbolOnCurrentLine = isNotInTheBeginning ? input[input.length - 1].charAt(quad.fromIndex() - 1) : '.';

            if (prevSymbolOnCurrentLine != '.') {
                validNumbers.add(quad);
                continue;
            }

            char nextSymbolOnCurrentLine = isNotAtTheEnd ? input[input.length - 1].charAt(quad.toIndex() + 2) : '.';

            if (nextSymbolOnCurrentLine != '.') {
                validNumbers.add(quad);
            }
        }

        int result = 0;
        for (int i = 0; i < input.length; i++) {
            char[] line = input[i].toCharArray();
            int index = i;
            List<Quad> validNumberFromAdjacentLines = validNumbers.stream()
                    .filter(quad -> quad.rowIndex() >= index - 1 && quad.rowIndex() <= index + 1).toList();
            for (int j = 0; j < line.length; j++) {
                if (!Character.isDigit(line[j]) && line[j] != '.') {
                    int atIndex = j;
                    List<Integer> gears = validNumberFromAdjacentLines.stream()
                            .filter(quad -> quad.fromIndex() - 1 <= atIndex && quad.toIndex() + 1 >= atIndex)
                            .map(Quad::value)
                            .toList();
                    if (gears.size() > 1) result += gears.get(0) * gears.get(1);
                }
            }
        }

        return result;
    }

}

record Triple(int fromIndex, int toIndex, int value) {}
record Quad(int rowIndex, int fromIndex, int toIndex, int value) {}
