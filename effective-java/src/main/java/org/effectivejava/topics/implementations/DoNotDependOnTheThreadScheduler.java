package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class DoNotDependOnTheThreadScheduler implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_11;
    }

    @Override
    public String getTheme() {
        return "Don't depend on the thread scheduler";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "when many threads are runnable, thread scheduler determines which ones get to run and for how long",
                "any program that relies on the thread scheduler for correctness or performance is likely to be " +
                        "non-portable; because thread scheduler is on OS level",
                "the best practice is to ensure that the average number of runnable threads is not significantly " +
                        "greater than number of processors",
                "thread should not run if they aren't doing useful work",
                "resist the temptation to 'fix' the program by putting in calls to Thread.yield",
                "Thread.yield has no testable semantics",
                "thread priorities are among the least portable features of Java"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
