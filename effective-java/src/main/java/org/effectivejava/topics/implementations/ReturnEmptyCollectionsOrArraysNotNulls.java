package org.effectivejava.topics.implementations;

import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

public class ReturnEmptyCollectionsOrArraysNotNulls implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_8;
    }

    @Override
    public String getTheme() {
        return "Return empty collections or arrays, not nulls";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "never return null in place of an empty array or collection",
            "there is argument that returning empty collection requires allocation and so - it has bad performance, " +
                    "so null should be returned",
            "it is unlikely that allocation affect your performance",
            "also, you can use immutable empty collections or arrays, for example Collections.emptyList()",
            "never preallocate the array, it harms performance, meaning never do this - k.toArray(new Foo[list.size()])",
            "also returning null force clients of code always to check for null"
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
