package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.Collection;
import java.util.List;

@AutoService(Item.class)
public class DesignInterfacesForPosterity implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "Design interfaces for posterity";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "It is about creating interfaces for ages. For example in Java 8 there default methods were provided. " +
                        "So they were added to old interfaces, for example - Collection. The default implementation " +
                        "cannot fit for all of the existing implementations, and it was a bit problem. The origin reasons " +
                        "for default methods - were lambdas and streams",
                "In the presence of default methods, existing implementation of an interface may compile without errors " +
                        "or warning but fail at runtime"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                Collection.class // removeIf default method can fail for 3rd party Concurrent libs, ex - apache SynchronizedCollection
        );
    }
}
