package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class MinimizeMutability implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "Minimize mutability";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "An immutable class - a class whose instances cannot be modified. " +
                        "All of the info contained in each instance is fixed for the lifetime of the object",
                ""
        );
    }

    @Override
    public void runExamples() {

    }
}
