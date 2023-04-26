package org.effectivejava.topics.abstractions;

import org.effectivejava.topics.helpers.Chapter;

import java.util.Collections;
import java.util.List;

public interface Item {

    Chapter getChapter();

    String getTheme();

    List<String> getBulletPoints();

    void runExamples();

    default List<Class<?>> examplesInCode() {
        return Collections.emptyList();
    }

}
