package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class ForInstanceControlPreferEnumTypesToReadResolve implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_12;
    }

    @Override
    public String getTheme() {
        return "For instance control, prefer enum types to readResolve";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "readResolve feature allows you to substitute another instance for the one created by readResolve (" +
                        "useful for singletons)",
                "if you depend on readResolve for instance control, all instance fields with object reference types " +
                        "must be declared transient",
                "the accessibility of readResolve is significant. In final class it should be private. If it is in " +
                        "not final class - consider accessibility (if private - not visible for subclasses, etc)",
                "with a special attack it is possible to create another instance of singleton object (if it is not " +
                        "declared as enum)"
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
