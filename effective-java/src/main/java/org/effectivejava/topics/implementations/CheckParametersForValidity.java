package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.math.BigInteger;
import java.util.List;

@AutoService(Item.class)
public class CheckParametersForValidity implements Item {

    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_8;
    }

    @Override
    public String getTheme() {
        return "Check parameters for validity";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "validate parameters in the beginning of the method, otherwise way can result in a violation of " +
                    "failure atomicity",
                "for public and protected methods use @throws in javadoc if a method can throw an exception",
                "for example mod method in BigInteger, it documents one error, but NPE documented on class level",
                "the objects.requireNonNull method, added in Java 7, is flexible and convenient, so there is no reason " +
                        "to perform null checks manually anymore",
                "objects.checkRange - useful methods"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
            BigInteger.class // mod method and class documentation about NPE
        );
    }
}
