package org.practicalunittesting;

public class StringUtils {

    public static String reverse(String s) {
        if (s == null)
            throw new IllegalArgumentException("Provided input string for reverse is null!");

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = temp;
        }
        return new String(chars);
    }

}
