package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.ArrayList;
import java.util.List;

@AutoService(Item.class)
public class EliminateUncheckedWarnings implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_5;
    }

    @Override
    public String getTheme() {
        return "Eliminate unchecked warnings";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "when you work with generics - you can get lots of types of warnings: unchecked cast warnings, " +
                        "unchecked method invocation warnings, unchecked parametrized vararg type warnings, " +
                        "unchecked conversion warnings",
                "'<>' - diamond operator",
                "eliminate every unchecked warning that you can",
                "elimination of all warnings - means you will not get a ClassCastException at runtime",
                "if you can't eliminate a warning, but you can prove that the code that provoked the warning is typesafe, " +
                        "the (and only then) suppress the warning with an @SuppressWarnings(\"unchecked\") annotation",
                "annotation @SuppressWarnings can be used on every declaration",
                "always use it on the smallest scope possible, look at toArray method in ArrayList",
                "every time you use a @SuppressWarnings(\"unchecked\") annotation, add a comment saying why it is safe to do so"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                ArrayList.class // toArray method
        );
    }
}
