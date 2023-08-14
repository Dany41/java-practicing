package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.io.ObjectStreamConstants;
import java.util.List;

@AutoService(Item.class)
public class UseInterfacesOnlyToDefineTypes implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "Use interfaces only to define types";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "When a class implements an interface, the interface serves as a type that can be used to refer to " +
                        "instances of the class",
                "It is inappropriate to use interfaces in other way",
                "Anti-pattern - Interface constants, see PhysicalConstants below",
                "The constant interface pattern is a poor use of interfaces"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                ObjectStreamConstants.class
        );
    }

    interface PhysicalConstants {
        static final double AVOGADROS_NUMBER = 6.022_140_857e23;
        static final double BOLTZMAN_CONSTANT = 1.380_648_52e-23;
        static final double ELECTRON_MASS = 9.109_383_56e-31;
    }
}
