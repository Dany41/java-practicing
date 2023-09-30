package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class UseCheckedExceptionsForRecoverableConditionsAndRuntimeExceptionsForProgrammingErrors implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_10;
    }

    @Override
    public String getTheme() {
        return "Use checked exceptions for recoverable conditions and runtime exceptions for programming errors";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "there are three kind of throwables: checked exceptions, runtime exceptions, and errors",
                "use checked exceptions for conditions from which the caller can reasonably be expected to recover",
                "checked exception -> force to catch",
                "there are two types of unchecked exceptions: runtime exceptions and errors",
                "unchecked exceptions should not be caught, because it is supposed that if such exceptions happened " +
                        "-> the further execution of program will do more harm than good",
                "use runtime exceptions to indicate programming errors",
                "there is a strong convention that errors are reserved for use by the JVM to indicate resource " +
                        "deficiencies, invariant failures, or others",
                "all of the unchecked throwables you implement should subclass RuntimeException class"
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
