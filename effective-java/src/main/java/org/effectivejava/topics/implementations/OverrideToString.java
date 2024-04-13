package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class OverrideToString implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_3;
    }

    @Override
    public String getTheme() {
        return "Always override toString";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "default toString returns ClassName@HashCode",
                "contract of toString: a concise but informative representation that is easy for a person to read",
                "it is recommended that all subclasses override this method",
                "the correct implementation is not vital, but classes become easier to use and easier to debug",
                "toString is automatically called when passed to println, printf functions",
                "when practical, the toString method should return all of the interesting information contained in the Object",
                "provide programmatic access to values contained in the toString, if you don't - you force users to extract if from result of toString"
        );
    }

    @Override
    public void runExamples() {

    }
}
