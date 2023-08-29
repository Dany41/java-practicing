package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class FavorGenericTypes implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_5;
    }

    @Override
    public String getTheme() {
        return "Favor generic types";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
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
