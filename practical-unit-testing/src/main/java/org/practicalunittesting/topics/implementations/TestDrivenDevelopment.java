package org.practicalunittesting.topics.implementations;

import org.abstractions.Chapter;
import org.abstractions.Item;
import org.practicalunittesting.topics.helpers.PutChapter;

import java.util.List;

public class TestDrivenDevelopment implements Item {
    @Override
    public Chapter getChapter() {
        return PutChapter.WRITING_UNIT_TESTS;
    }

    @Override
    public String getTheme() {
        return "Test Driven Development";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "two approaches of writing code: code-first and test-first",
                "test-first approach is about Test Driven Development",
                "thinking about tests and writing them before not only does the code work better, but 'feel and look' better, " +
                        "meaning the design is getting better",
                "the recommended book about TDD - 'Growing Object-Oriented Software, Guided by Tests (Beck\n" +
                        "Signature)'",
                "you should use each approach when appropriate",
                "code-first approach: advantage - write tests when the functionality is well understood; disadvantage - " +
                        "developer concentrates on testing the implementation instead of testing an interface (behavior) " +
                        "of the SUT",
                "the disadvantage can lead to: tests and code are highly coupled, encourage developers to write 'happy path' " +
                        "tests only",
                "also, with code-first approach there is a temptation not to write tests at all",
                "when working with legacy code - the code-first approach could be the only option",
                "test-first approach: force developers think about expected behavior of code, not exact implementation",
                "it cuts the functionality to a minimum required to work",
                "it results in 100% (or almost) test coverage",
                "write test always when bugs occur. If you do not - there is chance the bug will reappear, for example, " +
                        "when the fix is reverted",
                "TDD rule: never write code without a failing test",
                "the TDD rhythm: (choose a test); (write the test, make it compile, see it fails); (implement the code, " +
                        "run the whole test suite), (introduce changes but do not add new functionality), (run the whole " +
                        "test suite again)",
                "TDD means writing code in baby steps",
                "why write test and see it fails: you can be sure the functionality does not work, once it is implemented " +
                        "you will see it works",
                "also pay attention to assertion message when test fails, so it is understandable what the problem is",
                "refactoring phase is also suitable for adding some documentation",
                "TDD helps with, but does not guarantee, good design & good code",
                "writing tests first requires a good understanding of the technologies used, and knowledge of the problem " +
                        "domain, if you lack these - use code-first approach",
                "legacy code also does not allow to use TDD properly",
                "write good assertion messages without waiting for the test to fail",
                "always check if the test was executed at all",
                "TDD is not only about unit testing"
        );
    }

    @Override
    public void runExamples() {

    }
}
