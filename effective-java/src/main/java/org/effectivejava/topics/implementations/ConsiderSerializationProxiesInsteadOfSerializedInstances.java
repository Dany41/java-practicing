package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.EnumSet;
import java.util.List;

@AutoService(Item.class)
public class ConsiderSerializationProxiesInsteadOfSerializedInstances implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_12;
    }

    @Override
    public String getTheme() {
        return "Consider serialization proxies instead of serialized instances";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                """
                        serialization proxy pattern - design private static nested class that concisely represents the
                        logical state of an instance of the enclosing class. This nested class is known as serialization
                        proxy of the enclosing class. It should have a single constructor, whose parameter type is the
                        enclosing class. This constructor merely copies the data from its argument. Both the enclosing
                        class and its serialization proxy must be declared to implement Serializable""",
                """
                        Then add the following writeReplace method into enclosed class:
                        // writeReplace method for the serialization proxy pattern
                        private Object writeReplace() {
                            return new SerializationProxy(this);
                        }""",
                """
                        to prevent class from attacks - insert also this method:
                        // readObject method for the serialization proxy pattern
                        private void readObject(ObjectInputStream stream) throws InvalidObjectException {
                            throw new InvalidObjectException("Proxy required");
                        }""",
                "finally, provide a readResolve method on the SerializationProxy class"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                EnumSet.class // for initializing JumboEnumSet or RegularEnumSet
        );
    }
}
