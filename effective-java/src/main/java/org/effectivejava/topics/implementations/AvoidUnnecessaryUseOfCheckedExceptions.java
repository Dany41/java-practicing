package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class AvoidUnnecessaryUseOfCheckedExceptions implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_10;
    }

    @Override
    public String getTheme() {
        return "Avoid unnecessary use of checked exceptions";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "checked exceptions force programmers to deal with problems, enhancing readability",
                "if recovery may be possible and you want to force callers to handle exceptional conditions, first " +
                        "consider returning an optional"
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
