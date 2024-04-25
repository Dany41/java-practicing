package org.practicalunittesting.topics.implementations;

import org.abstractions.Chapter;
import org.abstractions.Item;
import org.practicalunittesting.topics.helpers.PutChapter;

import java.util.List;

public class UnitTestsWithNoCollaborators implements Item {
    @Override
    public Chapter getChapter() {
        return PutChapter.WRITING_UNIT_TESTS;
    }

    @Override
    public String getTheme() {
        return "Unit Tests with no Collaborators";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "typical project packaging: production code: src/main/java/*; tests: src/test/java/*;" +
                        "test resources: src/test/resources/",
                "annotation @Test is required for JUnit to recognize a method as test method",
                "states of test result: passed, failed, skipped, error",
                "main assertion method from affertj is assertThat method",
                "assertThat does not do anything on its own, it sets the stage for the following methods",
                "parametrized tests' idea: split the test method into two parts: source of arguments and actual testing",
                "use assertThatExceptionOfType() method to check if exception is thrown",
                "do not catch to generic exceptions, like Exception",
                "use meaningful names for parameters, not just input/expected",
                "test fixture - the context that should be set up for repetitive text execution",
                "test fixture examples: unit test - creation of SUT/DOCs; preparation of input data, integration tests - " +
                        "resetting db to initial state; copying of files. end-to-end tests: installation of VM, web server, db",
                "method annotated with @BeforeEach - is executed before each test method",
                "JUnit creates a new instance of a test class before executing each test method marked with @Test annotation",
                "another similar annotations: @AfterAll, @AfterEach, @BeforeEach",
                "setUp name of method is conventional; in JUnit 3.x it was mandatory, but now JUnit relies on annotations",
                "unit tests care about three things: creates SUT/DOCs, executes SUT's method, verifies the result",
                "such test are often described as Arrange/Act/Assert tests",
                "arrange - preparation of a test fixture",
                "it is recommended that all assertions within a single test method should verify properties of a single " +
                        "object: the SUT",
                "assertions on many objects within a single test method is a bad practice"
        );
    }

    @Override
    public void runExamples() {

    }
}
