package org.adventofcode;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.Charset;

public class Utils {
    public static String[] getInput(String filePathString) {
        try {
            return Resources.toString(Resources.getResource(filePathString), Charset.defaultCharset()).split("\r\n");
        } catch (IOException e) {
            System.out.println("Couldn't read file by path: " + filePathString);
            return new String[]{};
        }
    }

    public static char[][] stringArrayToChars(String[] input) {
        int height = input.length;
        int width = input[0].length();
        char[][] chars = new char[height][width];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                chars[i][j] = input[i].charAt(j);
            }
        }
        return chars;
    }

    public static char[][] transpose(char[][] chars) {
        char[][] transposed = new char[chars[0].length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                transposed[j][i] = chars[i][j];
            }
        }
        return transposed;
    }
}
