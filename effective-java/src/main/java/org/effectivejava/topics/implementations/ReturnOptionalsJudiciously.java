package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class ReturnOptionalsJudiciously implements Item {

    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_8;
    }

    @Override
    public String getTheme() {
        return "Return optionals judiciously";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "never return a null value from an Optional-returning method",
                "optionals are similar in spirit to checked exceptions",
                "container types, including collections, maps, streams, arrays, and optionals should not be wrapped " +
                        "in optionals",
                "you should declare a method to return Optional<T> if it might not be able to return a result and " +
                        "clients wil have to perform special processing if no result is returned",
                "allocating optional objects and then unwrapping it is costly for performance-critical situations",
                "you should never return an optional of a boxed primitive type (use OptionalInt, etc classes)",
                "it is almost never appropriate to use an optional as a key, value, or element in a collection or array"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
            ProcessHandle.class // method arguments returns array in optional
        );
    }
}
