package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class UseExceptionsOnlyForExceptionalConditions implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_10;
    }

    @Override
    public String getTheme() {
        return "Use exceptions only for exceptional cases";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "exceptions are, as their name implies, to be used only for exceptional conditions; they should never " +
                        "be use for ordinary control flow",
                "a well-designed API must not force its clients to use exceptions for ordinary control flow"
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
