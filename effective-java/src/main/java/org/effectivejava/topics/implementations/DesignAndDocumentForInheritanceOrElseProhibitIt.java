package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.time.Instant;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.List;

@AutoService(Item.class)
public class DesignAndDocumentForInheritanceOrElseProhibitIt implements Item {
    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "The class, if it is intended to be subclassed - must clearly state in the documentation",
                "The class must document its self-use of overridable methods (check AbstractCollection#remove + @implSpec)",
                "For class to be safely subclassed - you must specify implementation details in documentation to " +
                        "ensure the stability",
                "A class may have to provide hooks into its internal workings in the form of judiciously chosen " +
                        "protected methods (see AbstractList#removeRange)",
                "Constructor must not invoke overridable methods (The superclass constructor runs before the " +
                        "subclass constructor, see example with class Super below)",
                "To prohibit subclassing - make the class private or change all the access modifiers of " +
                        "constructors to private and provide factory method"
        );
    }

    @Override
    public String getTheme() {
        return "Design and document for inheritance or else prohibit it";
    }

    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_4;
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                AbstractCollection.class, // example for @implSpec
                AbstractList.class // example for hook in internal working
        );
    }

    @Override
    public void runExamples() {
        Sub sub = new Sub();
        sub.overrideMe();
    }

    private static class Super {
        // Broken - constructor invokes an overridable method
        public Super() {
            overrideMe();
        }
        public void overrideMe() {
        }
    }

    private static final class Sub extends Super {
        // Blank final, set by constructor
        private final Instant instant;
        Sub() {
            instant = Instant.now();
        }
        // Overriding method invoked by superclass constructor
        @Override
        public void overrideMe() {
            System.out.println(instant);
        }
    }
}
