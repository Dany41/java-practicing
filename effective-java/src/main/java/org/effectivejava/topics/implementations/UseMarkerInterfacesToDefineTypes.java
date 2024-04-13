package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.io.Serializable;
import java.util.List;

@AutoService(Item.class)
public class UseMarkerInterfacesToDefineTypes implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_6;
    }

    @Override
    public String getTheme() {
        return "Use marker interfaces to define types";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "marker interface - interface that contains no method declarations, but just marks a class that implements " +
                    "the interface as having some property",
                """
                    marker interfaces have two advantages over annotations:
                        marker interfaces define a type that is implemented by instances of the marked class; marker
                            annotations do not (meaning you can catch errors in compile-time);
                        marker interfaces can be targeted more precisely""",
            "the chief advantage of marker annotations over marker interfaces is that they are part of the larger " +
                    "annotation facility",
            "use annotation if the marker applies to any program element other than class or interface",
            """
                    if marker applies only to classes and interfaces, ask yourself the question: 'might I want to write
                    one or more methods that accept only objects that have this markings?'. If so, you should use the
                    marker interface""",
            "if you find yourself writing a marker annotation type whose target is ElementType.TYPE, the the time to " +
                    "figure out whether it really should be an annotation type or whether a marker interface would be better"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                // if a class marked - it can be written to ObjectOutputStream; look at writeObject/write methods
                Serializable.class
        );
    }
}
