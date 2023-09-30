package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class DocumentAllExceptionsThrownByEachMethod implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_10;
    }

    @Override
    public String getTheme() {
        return "Document all exceptions thrown by each method";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "always declare checked exceptions individually, and document precisely the conditions under which " +
                        "each one is thrown",
                "'throws Exception' in contract of a method is appropriate only in main() method because it is called " +
                        "by VM",
                "use the Javadoc @throws tag to document each exception that a method can throw, but do not use the " +
                        "throws keyword on unchecked exceptions",
                "if an exception is thrown by many methods in a class for the same reason, you can document the " +
                        "exception in the class's documentation comment"
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
