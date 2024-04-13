package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class AvoidStringsWhereOtherTypesAreMoreAppropriate implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Avoid strings where other types are more appropriate";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "Strings are poor substitutes for other value types",
                "Strings are poor substitutes for enum types",
                "Strings are poor substitutes for aggregate types",
                "Strings are poor substitutes for capabilities",
                "avoid representing objects as strings when better data types exist or can be written"
        );
    }

    @Override
    public void runExamples() {
        // Inappropriate use of string as aggregate type
//        String compoundKey = className + "#" + i.next();
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    // Broken - inappropriate use of string as capability!
    private static class ThreadLocal {
        private ThreadLocal() { } // Noninstantiable
        // Sets the current thread's value for the named variable.
        public static void set(String key, Object value) {};
        // Returns the current thread's value for the named variable.
        public static Object get(String key) {
            return null;
        }
    }

    private static class ThreadLocalOk {
        private ThreadLocalOk() { } // Noninstantiable
        public static class Key { // (Capability)
            Key() { }
        }
        // Generates a unique, unforgeable key
        public static Key getKey() {
            return new Key();
        }
        public static void set(Key key, Object value) {};
        public static Object get(Key key) {
            return null;
        }
    }
}
