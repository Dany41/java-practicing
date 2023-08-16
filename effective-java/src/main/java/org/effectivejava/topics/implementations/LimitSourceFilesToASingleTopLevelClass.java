package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class LimitSourceFilesToASingleTopLevelClass implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "Limit source files to a single top level class";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "Look at Item25_Main",
                "Then check files Utensil and Dessert. They have definitions for classes Utensil and Dessert both",
                "Intellij does not allow this, but as from book - it was allowed before and it is the main reason " +
                        "not to write several classes in the one source file",
                "But, if we go to console and write javac Item25_Main.java Utensil.java - then launch - it will fail, " +
                        "but if we go with javac Utensil.java Item25_Main.java - it will launch just fine",
                "Outcome - never put multiple top-level classes or interfaces in a single source file"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Item.super.examplesInCode();
    }
}
