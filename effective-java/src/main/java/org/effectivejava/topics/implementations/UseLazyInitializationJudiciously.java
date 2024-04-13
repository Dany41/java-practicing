package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class UseLazyInitializationJudiciously implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_11;
    }

    @Override
    public String getTheme() {
        return "Use lazy initialization judiciously";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "in the presence of multiple threads, lazy initialization is tricky. If two or more threads share a " +
                        "lazily initialized field, it is critical that some form of synchronization is employed",
                "under most circumstances, normal initialization is preferable to lazy initialization",
                "if you use lazy initialization to break an initialization circularity, use a synchronized accessor",
                "if you need to use lazy initialization for performance on a static field, use the lazy initialization" +
                        "holder class idiom",
                "if you need to use lazy initialization for performance on an instance field, use the double-check idiom",
                "for double-check idiom it is critical for field to be volatile",
                """
                        summarizing lazily initialization techniques: instance fields - double-check idiom; static
                        fields - lazy initialization holder class idiom; instance fields that can tolerate repeated
                        initialization - single-check idiom"""
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
