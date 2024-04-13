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

        );
    }

    @Override
    public void runExamples() {

    }
}
