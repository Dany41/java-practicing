package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.Comparator;
import java.util.List;

@AutoService(Item.class)
public class PreferPrimitiveTypesToBoxedPrimitives implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Prefer primitive types to boxed primitives";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "every primitive type has a corresponding boxed primitive",
                """
                        There are three main differences between primitives boxed primitives:
                            - primitives has only their values, whereas boxed primitives have identities distinct from
                                their values; in other words, two boxed primitive instances can have the same value and
                                different identities
                            - primitive types have only fully functional values, whereas each boxed primitive type has
                                one nonfunctional value, which is null
                            - primitives are more time- and space-efficient than boxed primitives
                            
                        """,
                "applying the == operator to boxed primitives is almost always wrong",
                "when you mix primitives and boxed primitives in an operation, the boxed primitive is auto-unboxed",
                "autoboxing reduces the verbosity, but not the danger, of using boxed primitives",
                "when your program does unboxing, it can throw a NullPointerException",
                "when your program boxes primitives values, it can result in costly and unnecessary object creations"
        );
    }

    @Override
    public void runExamples() {
        // Broken comparator - can you spot the flaw?
        Comparator<Integer> naturalOrder =
                (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
