package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.AbstractSequentialList;
import java.util.List;

@AutoService(Item.class)
public class ThrowExceptionsAppropriateToTheAbstraction implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_10;
    }

    @Override
    public String getTheme() {
        return "Throw exceptions appropriate to the abstraction";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "higher layers should catch lower-level exceptions and, in their place, throw exceptions that can be " +
                        "explained in terms of the higher-level abstraction; this known as exception translation",
                """
                        a special form of exception translation called exception chaining is called for in cases where\
                        the lower-level exception might be helpful to someone debugging the problem that caused the
                        higher-level exception. The lower-level exception (the cause) is passed to the higher-level
                        exception, which provides an accessor method (Throwable's getCause method) to retrieve the
                        lowe-level exception:
                        // Exception Chaining
                        try {
                            ... // Use lower-level abstraction to do our bidding
                        } catch (LowerLevelException cause) {
                            throw new HigherLevelException(cause);
                        }""",
                "while exception translation is superior to mindless propagation of exceptions from lower layers, it " +
                        "should not be overused"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
            AbstractSequentialList.class // method get(int index)
        );
    }
}
