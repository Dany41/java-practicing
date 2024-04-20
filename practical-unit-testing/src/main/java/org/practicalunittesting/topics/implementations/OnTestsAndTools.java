package org.practicalunittesting.topics.implementations;

import org.abstractions.Chapter;
import org.abstractions.Item;
import org.practicalunittesting.topics.helpers.PutChapter;

import java.util.List;

public class OnTestsAndTools implements Item {
    @Override
    public Chapter getChapter() {
        return PutChapter.DEVELOPERS_TESTS;
    }

    @Override
    public String getTheme() {
        return "On Tests and Tools";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "SUT - system under test, in unit tests - classes, in end-to-end tests - application",
                "DOC - dependent on component, anything required for SUT to do its job",
                "DOCs = collaborators",
                "DOCs usually have the same level as SUT: classes, modules, etc",
                "test doubles = mocks, stubs, dummies",
                "there is no golden rules on: which tests write, in which proportion",
                "there are two groups of thoughts: do whatever it takes to create tests and if you can't write tests - " +
                        "your design sucks",
                "let's stop if you think that developers should not write tests",
                "JUnit is created by Kent Beck in 1997, he is co-author of the 'Refactoring' book",
                "Mockito is created nu Szczepan Faber in Q4 2008",
                "Cobertura - tool for code coverage",
                "PIT - mutation testing",
                "Awaitility - testing asynchronous code"
        );
    }

    @Override
    public void runExamples() {

    }
}
