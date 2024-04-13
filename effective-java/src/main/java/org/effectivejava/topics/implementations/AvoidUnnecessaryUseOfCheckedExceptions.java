package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class AvoidUnnecessaryUseOfCheckedExceptions implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_10;
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
