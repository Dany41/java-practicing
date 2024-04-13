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

        );
    }

    @Override
    public void runExamples() {

    }
}
