package org.adventofcode.y2023.day13;

import org.adventofcode.Utils;

import java.util.Arrays;

public class MirrorFinder {

    public static int calculateMirrorScore(char[][] frames) {
        for (int i = 0; i < frames.length; i++) {
            if (isMirror(i, frames)) return (i + 1) * 100;
        }
        char[][] transposedFrames = Utils.transpose(frames);
        for (int i = 0; i < transposedFrames.length; i++) {
            if (isMirror(i, transposedFrames)) return i + 1;
        }
        return 0;
    }

    public static int calculateMirrorScorePart2(char[][] frames) {
        for (int i = 0; i < frames.length; i++) {
            if (isMirror(i, frames, 1)) return (i + 1) * 100;
        }
        char[][] transposedFrames = Utils.transpose(frames);
        for (int i = 0; i < transposedFrames.length; i++) {
            if (isMirror(i, transposedFrames, 1)) return i + 1;
        }
        return 0;
    }

    public static boolean isMirror(int index, char[][] frames) {
        return isMirror(index, frames, 0);
    }

    public static boolean isMirror(int index, char[][] frames, int diffAllowed) {
        if (index == frames.length - 1) return false;
        int diffActual = 0;
        for (int i = index, j = index + 1; i >= 0 && j < frames.length; i--, j++) {
            diffActual += diffCount(frames[i], frames[j]);
        }
        if (diffActual != diffAllowed) return false;
        return true;
    }

    private static int diffCount(char[] chars1, char[] chars2) {
        int diff = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) diff++;
        }
        return diff;
    }

}
