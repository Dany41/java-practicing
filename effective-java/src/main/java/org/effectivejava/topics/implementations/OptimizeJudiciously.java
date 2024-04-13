package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.awt.*;
import java.util.List;

@AutoService(Item.class)
public class OptimizeJudiciously implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Optimize judiciously";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "strive to write good programs rather than fast ones",
                "strive to avoid design decisions that limit performance",
                "consider the performance consequences of your API design decisions",
                "it is a very bad idea to warp an API to achieve good performance",
                "measure performance before and after each attempted optimization",
                """
                        do not strive to write fast programs - strive to write good ones; speed will follow. But do
                        think about performance while you're designing systems, especially while you're designing APIs,
                        wire-level protocols, and persistent data formats"""
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                Component.class // getSize()
        );
    }
}
