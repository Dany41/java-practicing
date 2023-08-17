package org.effectivejava.topics.implementations;


import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;
import java.util.Set;

@AutoService(Item.class)
public class DoNotUseRawTypes implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_5;
    }

    @Override
    public String getTheme() {
        return "Don't use raw types";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "Generics has been in java since Java 5",
                "The compiler inserts invisible casts for you if you use generics",
                "If you use raw types, you lose all the safety and expressiveness benefits of generics",
                "In order to save compatibility - generics in java were implemented via erasure (Item 28)",
                "Subtyping: List<String> is subtype of List, but not subtype of List<Object>",
                "You lose type safety if you use a raw type such as List, but not if you use a parametrized " +
                        "type such as List<Object>",
                "Wildcards - '?' - Set<?> - read like 'set of some type'",
                "You can't put any element (other than null) into a Collection<?>",
                "You must use raw types in class literals. For example, List.class is legal, but List<String>.class is not",
                "The same with instanceof operator, below is an example of legitimate use of raw type - instanceof operator",
                "Using raw types can lead to exceptions at runtime"
        );
    }

    @Override
    public void runExamples() {
        Object o = new Object();
        if (o instanceof Set) { // Raw type
            Set<?> s = (Set<?>) o; // Wildcard type
//            ...
        }
    }
}
