package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class DesignMethodSignaturesCarefully implements Item {

    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_8;
    }

    @Override
    public String getTheme() {
        return "Design method signatures carefully";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "choose method names carefully",
                "don't go overboard in providing convenience methods",
                "avoid long parameter lists (aim for 4 or fewer)",
                "long sequences of identically typed parameters are especially harmful",
                "consider builder pattern, but for methods",
                "for parameter types, favor interfaces over classes",
                "prefer two-element enum types to boolean parameters"
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
