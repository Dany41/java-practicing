package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AutoService(Item.class)
public class ReferToObjectsByTheirInterfaces implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Refer to objects by their interfaces";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "if appropriate interface types exist, then parameters, return values, variables, and fields should all " +
                        "be declared using interface types",
                "if you get into the habit of using interfaces as types, your program will be much more flexible",
                "it is entirely appropriate to refer to an object by a class rather than an interface if no appropriate " +
                        "interface exists",
                "if there is no appropriate interface, just use the least specific class in the class hierarchy that " +
                        "provides the required functionality"
        );
    }

    @Override
    public void runExamples() {
        // Good - uses interface as type
        Set<Son> sonSet = new LinkedHashSet<>();

        // Bad - uses class as type!
        LinkedHashSet<Son> sonSetBad = new LinkedHashSet<>();

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    private static enum Some {
        s, a, d;
    }

    private static class Son {

    }
}
