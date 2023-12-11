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
}
