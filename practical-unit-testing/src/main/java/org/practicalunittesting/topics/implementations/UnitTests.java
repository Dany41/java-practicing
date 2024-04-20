package org.practicalunittesting.topics.implementations;

import org.abstractions.Chapter;
import org.abstractions.Item;
import org.practicalunittesting.topics.helpers.PutChapter;

import java.util.List;

public class UnitTests implements Item {
    @Override
    public Chapter getChapter() {
        return PutChapter.DEVELOPERS_TESTS;
    }

    @Override
    public String getTheme() {
        return "Unit Tests";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "unit tests: tests your code; focused on single classes; run in isolation; control context; fast",
            "unit tests are developer's responsibility",
            "they can help to improve your design",
            "state testing - is about testing 'workers' or classes which have no collaborators, also called - state " +
                    "verification",
            "interactions testing - is about testing 'managers' or classes which have collaborators, also called - " +
                    "behaviour verification"
        );
    }

    @Override
    public void runExamples() {

    }
}
