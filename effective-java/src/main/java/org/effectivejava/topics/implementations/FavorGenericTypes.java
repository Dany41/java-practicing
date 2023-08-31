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
                "generic types are safer and easier yo use than type that require casts in client code",
                "when you design new types, make sure that they can be used without such casts",
                "if you have any existing types that should be generic but aren't, generify them, it is safe as it is " +
                        "compatible"
        );
    }

    @Override
    public void runExamples() {

    }

}