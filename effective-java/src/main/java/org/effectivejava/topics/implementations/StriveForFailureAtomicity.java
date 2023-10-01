package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;
import java.util.Stack;

@AutoService(Item.class)
public class StriveForFailureAtomicity implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_10;
    }

    @Override
    public String getTheme() {
        return "Strive for failure atomicity";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "a failed method invocation should leave the object in the state that it was in prior to the " +
                        "invocation; the failure with this property is said to be failure-atomic",
                "immutable objects are failure-atomic for free, as the state of immutable object cannot be changed",
                """
                        ways to achieve failure-atomicity:
                            - before any computation do checks
                            - memento pattern
                            - recovery code - it intercepts a failure that occurs in the midst of an operation, and
                                causes the object to roll back its state to the point before operation began. This
                                approach is used mainly for durable (disk-based) data structures""",
                "it is not always achievable: if two threads attempt to modify the same object concurrently without " +
                        "proper synchronization, the object may be left in an inconsistent state",
                "even where failure-atomicity is possible, it is not always desirable. For some operations, it would " +
                        "significantly increase the cost or complexity"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                Stack.class // methods pop() -> peek()
        );
    }
}
