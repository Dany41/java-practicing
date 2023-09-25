package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class PreferForEachLoopsToTraditionalForLoops implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Prefer for-each loops to traditional for loops";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "there is no performance penalty for using for-each loops, even for arrays",
                "look at a book for exercise - spotting a bug",
                """
                    there are three situations where you can't use for-each loop:
                        - destructive filtering
                        - transforming
                        - parallel iteration""",
                "for-each loop lets you iterate over any object that implements the Iterable interface",
                "for-each loop provides compelling advantages over the traditional for loop in clarity, flexibility, " +
                        "and bug prevention, with no performance penalty"
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
