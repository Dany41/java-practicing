package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class ImplementSerializableWithGreatCaution implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_12;
    }

    @Override
    public String getTheme() {
        return "Implement Serializable with great caution";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "a major cost of implementing Serializable is that it decreases the flexibility to change a class's " +
                        "implementation once it has been released",
                """
                    every serializable class has a unique identification number associated with it. If you do not
                    specify this number by declaring a static final long field named serialVersionUID, the system
                    automatically generates it at runtime by applying a cryptographic hash function (SHA-1) to the
                    structure of the class""",
                "a second cost of implementing Serializable is that it increases the likelihood of bugs and security " +
                        "holes",
                "deserialization can be called hidden constructor - so it can violate invariants, etc",
                "a third cost of implementing Serializable is that it increases the testing burden associated with " +
                        "releasing a new version of a class",
                "implementing Serializable is not a decision to be undertaken lightly",
                "classes designed for inheritance should rarely implement Serializable, and interfaces should rarely " +
                        "extend it",
                """
                        if the class has invariants that would be violated if its instance fields were initialized to their
                        default values, you must add method readObjectNoData: {
                            private void readObjectNoData() throws InvalidObjectException {
                                throw new InvalidObjectException("Stream data required");
                            }
                        }""",
                "inner classes should not implement Serializable"
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
