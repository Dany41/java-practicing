package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class BewareThePerformanceOfStringConcatenation implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Beware the performance of string concatenation";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
               "using the string concatenation operator repeatedly to concatenate n strings requires time quadratic in n",
               "to achieve acceptable performance, use a StringBuilder in place of a String",
               "don't use the string concatenation operator to combine more than a few strings, unless performance is " +
                       "irrelevant"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
