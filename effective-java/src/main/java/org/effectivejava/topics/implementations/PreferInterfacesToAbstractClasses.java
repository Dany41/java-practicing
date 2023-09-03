package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.AbstractList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AutoService(Item.class)
public class PreferInterfacesToAbstractClasses implements Item {

    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "Prefer interfaces to abstract classes";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "After providing default method implementations in interfaces, abstract classes almost does not " +
                        "have any advantages above interfaces. Class can implement multiple interfaces, but still " +
                        "can subclass only one abstract class",
                "Interfaces are ideal for defining mixins. Mixin is interfaces which allow you to add additional " +
                        "functionality to your class by implementing it (for example Comparable)",
                "Interfaces allow for the construction of non-hierarchical type frameworks (see Singer, Songwriter ex below)",
                "There is a way to combine advantages of interfaces and abstract classes by providing an abstract " +
                        "skeletal implementation class. The interface defines the type, while the skeletal implementation " +
                        "class implements the remaining noon-primitive interface methods atop the primitive interface methods. " +
                        "This approach is known as Template Method pattern (for example AbstractCollection, " +
                        "AbstractSet, etc). Their use can simplify the implementation of interfaces, see static List method below",
                "Static method with AbstractList below is an example of Adapter pattern that allows an int array to be " +
                        "viewed as a list of Integer instances",
                "AbstractMapEntry is one more example below. Note that implementations could not be implemented in " +
                        "interfaces (you can't implement Object's methods in interfaces, default methods are not " +
                        "permitted to override Object's methods)",
                "As skeletal implementations are created to be extended - all the rules from the previous Item about " +
                        "the documenting is applied here"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Item.super.examplesInCode();
    }

    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        return new AbstractList<>() {
            @Override
            public Integer get(int index) {
                return a[index];
            }

            @Override
            public Integer set(int index, Integer val) {
                int oldVal = a[index];
                a[index] = val;
                return oldVal;
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }

    interface Singer {
        void sing(String s);
    }

    interface Songwriter {
        String compose(int chartPosition);
    }

    interface SingerSongwriter extends Singer, Songwriter {
        void strum();
        void actSensitive();
    }

    // Skeletal implementation class
    public abstract class AbstractMapEntry<K,V>
            implements Map.Entry<K,V> {
        // Entries in a modifiable map must override this method
        @Override public V setValue(V value) {
            throw new UnsupportedOperationException();
        }
        // Implements the general contract of Map.Entry.equals
        @Override public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry) o;
            return Objects.equals(e.getKey(), getKey())
                    && Objects.equals(e.getValue(), getValue());
        }
        // Implements the general contract of Map.Entry.hashCode
        @Override public int hashCode() {
            return Objects.hashCode(getKey())
                    ^ Objects.hashCode(getValue());
        }
        @Override public String toString() {
            return getKey() + "=" + getValue();
        }
    }
}
