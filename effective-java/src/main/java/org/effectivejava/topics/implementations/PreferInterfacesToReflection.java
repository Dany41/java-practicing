package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class PreferInterfacesToReflection implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Prefer interfaces to reflection";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                """
                        reflection drawbacks:
                            - you lose all the benefits of compile-time type checking
                            - the code required to perform reflective access is clumsy and verbose
                            - performance suffers""",
                "you can obtain many of the benefits of reflection while incurring few of its costs by using it only in " +
                        "a very limited form",
                "you can create instances reflectively and access them normally via their interfaces or superclass"
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
