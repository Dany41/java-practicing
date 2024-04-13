package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import lombok.RequiredArgsConstructor;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

@AutoService(Item.class)
public class ConsiderUsingACustomSerializedForm implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_12;
    }

    @Override
    public String getTheme() {
        return "Consider using a custom serialized form";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "do not accept the default serialized form without first considering whether it is appropriate",
                "the default serialized form is likely to be appropriate if an object's physical representation is " +
                        "identical to its logical content",
                "even if you decide that the default serialized form is appropriate, you often must provide a " +
                        "readObject method to ensure invariants and security",
                """
                        using the default serialized form when an object's physical representation differs substantially
                        from its logical data content has four disadvantages:
                            - it permanently ties the exported API to the current internal representation
                            - it can consume excessive space
                            - it can consume excessive time
                            - it can cause stack overflows""",
                "before deciding to make a field non-transient, convince yourself that its value is part of the " +
                        "logical state of the object",
                "if you are using the default serialized form and you have labeled one or more fields transient, " +
                        "remember that these fields will be initialized to their default values",
                "you must impose any synchronization on object serialization that you would impose on any other " +
                        "method that reads the entire state of the object",
                "regardless of what serialized form you choose, declare an explicit serial version UID in every " +
                        "serializable class you write",
                "do not change the serial version UID unless you want to break compatibility with all existing " +
                        "serialized instances of a class"
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

    // Good candidate for default serialized form
    @RequiredArgsConstructor
    public static class Name implements Serializable {
        /**
         * Last name. Must be non-null.
         * @serial
         */
        private final String lastName;
        /**
         * First name. Must be non-null.
         * @serial
         */
        private final String firstName;
        /**
         * Middle name, or null if there is none.
         * @serial
         */
        private final String middleName;
         // Remainder omitted
    }

    // StringList with a reasonable custom serialized form
    public static class StringList implements Serializable {
        private transient int size = 0;
        private transient Entry head = null;
        // No longer Serializable!
        private static class Entry {
            String data;
            Entry next;
            Entry previous;
        }
        // Appends the specified string to the list
        public final void add(String s) {  }
        /**
         * Serialize this {@code StringList} instance.
         *
         * @serialData The size of the list (the number of strings
         * it contains) is emitted ({@code int}), followed by all of
         * its elements (each a {@code String}), in the proper
         * sequence.
         */
        private void writeObject(ObjectOutputStream s)
                throws IOException {
            s.defaultWriteObject();
            s.writeInt(size);
            // Write out all elements in the proper order.
            for (Entry e = head; e != null; e = e.next)
                s.writeObject(e.data);
        }
        private void readObject(ObjectInputStream s)
                throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            int numElements = s.readInt();
            // Read in all elements and insert them in list
            for (int i = 0; i < numElements; i++)
                add((String) s.readObject());
        }
        // Remainder omitted
    }

}
