package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@AutoService(Item.class)
public class FavorStaticMemberClassesOverNonStatic implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "Favor static member classes over nonstatic";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "Nested classes should serve only its enclosing class, if it is useful in some another context - " +
                        "it can be a top-level class",
                "There are four kinds of nested classes: static member classes, nonstatic member classes, " +
                        "anonymous classes, local classes",
                "All, but the first are known as inner classes",
                "Each instance of a nonstatic member class is implicitly associated with an enclosing instance of " +
                        "its containing class",
                "Within instance methods of a nonstatic member class, you can invoke methods on the enclosing instance " +
                        "or obtain a reference to the enclosing instance using the qualified this construct ??",
                "If the instance of nested class can exist without instance of enclosing one - it must be static",
                "But, it is possible to create an instance of a nonstatic member class without an enclosing instance",
                "The association between a nonstatic member class instance and its enclosing instance is established " +
                        "when the member class instance is created and cannot be modified thereafter",
                "It is possibly to set the association manually via: enclosingInstance.new MemberClass(args)",
                "Common use of a nonstatic member class is to define an Adapter that allows an instance of the outer " +
                        "class to be viewed as an instance of some unrelated class",
                "If you declare a member class that does not require access to an enclosing instance, always put the " +
                        "static modifier in its declaration (nonstatic member requires association with outer class: " +
                        "additional time + space consumed)"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                // Adapter examples
                List.class,
                Set.class,
                Map.class
        );
    }
}
