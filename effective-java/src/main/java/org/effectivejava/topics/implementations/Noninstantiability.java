package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AutoService(Item.class)
public class Noninstantiability implements Item {

    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_2;
    }

    @Override
    public String getTheme() {
        return "Enforce noninstantiability with a private constructor";
    }

    @Override
    public List<String> getBulletPoints() {
        return Arrays.asList(
                "there is no way to enforce noninstantiability via abstract classes, as the class can be subclassed",
                "if class has no explicit constructors - compiler will create empty default one",
                "if at least one explicit constructor is created - the additional will not be generated",
                "by creating empty explicit private constructor you can reach noninstantiability",
                "throw an exception in that constructor to prevent creating via reflection"
        );
    }

    @Override
    public void runExamples() {
        System.out.println("Trying to create class instance via reflection...");
        Constructor<?> utilityCons = UtilityClass.class.getDeclaredConstructors()[0];
        utilityCons.setAccessible(true);
        try {
            System.out.println("Created instance: " + utilityCons.newInstance());
        } catch (Exception e) {
            System.out.println("Oops, error occurred: " + e.getMessage());
        }
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Arrays.asList(
                Math.class,
                Arrays.class,
                Collections.class
        );
    }

    // example from book
    // noninstantiable utility
    public static class UtilityClass {
        // Suppress default constructor
        private UtilityClass() {
            throw new AssertionError();
        }
    }

}
