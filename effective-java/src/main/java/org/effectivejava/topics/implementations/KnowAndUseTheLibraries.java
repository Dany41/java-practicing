package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;
import java.util.Random;

@AutoService(Item.class)
public class KnowAndUseTheLibraries implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Know and use the libraries";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "imagine you have a task to generate a random number with some upper bound, you will do something like " +
                        "method random below",
                """
                    that approach has 3 flaws:
                        - if n is a small power of two - the sequence of random numbers will repeat itself after a fairly
                            short period
                        - if n is not a power of two, some numbers will, on average, be returned more frequently than
                            others
                        - on rare occasions, random can fail catastrophically, returning a number outside the specified
                            range""",
                """
                        the third flaw can happen because method attempts to map the value returned by rnd.nextInt() to
                        a non-negative int by calling Math.abs. If nextInt() return Integer.MIN_VALUE, Math.abs will
                        also return Integer.MIN_VALUE, and the remainder operator (%) will return a negative number,
                        assuming n is not a power of two""",
                "Random.nextInt(int) fixes problems",
                "by using a standard library, you take advantage of the knowledge of the experts who wrote it and the " +
                        "experience of those who used it before you",
                "after Java 7 the random number generator of choice is now ThreadLocalRandom",
                "numerous features are added to the libraries in every major release, and it pays to keep abreast of " +
                        "these additions",
                "every programmer should be familiar with the basics of java.lang, java.util, and java.io, and their " +
                        "subpackages"
        );
    }

    @Override
    public void runExamples() {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 1000000; i++)
            if (random(n) < n/2)
                low++;
        System.out.println(low);
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    // Common but deeply flawed!
    private static Random rnd = new Random();

    private static int random(int n) {
        return Math.abs(rnd.nextInt()) % n;
    }
}
