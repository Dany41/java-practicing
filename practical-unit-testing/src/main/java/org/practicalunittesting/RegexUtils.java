package org.practicalunittesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RegexUtils {
    public static List<Integer> extractDigits(String input) {
        List<Integer> result = new ArrayList<>();

        int counter = 0;
        StringBuilder extractedDigits = new StringBuilder();

        for (int character : input.chars().toArray()) {
            boolean isDigit = Character.isDigit(character);
            if (isDigit) {
                counter++;
                extractedDigits.append((char)character);
            } else {
                if (counter >= 3) {
                    result.add(Integer.valueOf(extractedDigits.toString()));
                    extractedDigits = new StringBuilder();
                }
                counter = 0;
            }
        }

        if (!extractedDigits.isEmpty() && counter >= 3)
            result.add(Integer.valueOf(extractedDigits.toString()));

        return result;
    }
}
