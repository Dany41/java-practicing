package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AutoService({Item.class})
public class PublicClassesPreferAccessorMethods2Fields implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "In public classes, use accessor methods, not public fields";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                """
                    Degenerate Public class below is bad, because: it doesn't offer the benefits of encapsulation:" +
                        you can't change the representation without changing the API
                        you can't take auxiliary action in case of accessing the field
                        you can't enforce invariants""",
                "Create getter and setters (if needed) and make the fields private if needed. Only if class is exposed to the client. " +
                    "Sometimes there is no need in this if it is private or for the external usage",
                "Bad examples in Java standard library: java.awt.Dimension and java.awt.Point",
                // todo
                "Accessor methods for final fields are also questionable, see last ex in an Item for example of invariants"
        );
    }

    @Override
    public void runExamples() {

    }

    // TAKEN FROM BOOK
    // Degenerate classes like this should not be public!
    public static class Point {
        public double x;
        public double y;
    }
}
