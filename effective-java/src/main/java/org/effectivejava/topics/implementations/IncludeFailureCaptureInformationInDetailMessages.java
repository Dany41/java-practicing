package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class IncludeFailureCaptureInformationInDetailMessages implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_10;
    }

    @Override
    public String getTheme() {
        return "Include failure capture information in detail messages";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "to capture a failure, the detail message of an exception should contain the values of all parameters " +
                        "and fields that contributed to the exception",
                "do not include passwords, encryption keys, and the like in detail messages"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                IndexOutOfBoundsException.class // constructor forces to pass 'index'
        );
    }
}
