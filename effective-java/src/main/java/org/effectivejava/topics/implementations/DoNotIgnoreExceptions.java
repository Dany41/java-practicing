package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class DoNotIgnoreExceptions implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_10;
    }

    @Override
    public String getTheme() {
        return "Don't ignore exceptions";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "an empty catch block defeats the purpose of exceptions",
                "if you choose to ignore an exception, the catch block should contain a comment explaining why it is " +
                        "appropriate to do so, and the variable should be named ignored"
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
