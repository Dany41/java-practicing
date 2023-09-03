package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import com.google.common.collect.ForwardingCollection;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.*;


// todo: rework
@AutoService(Item.class)
public class FavorCompositionOverInheritance implements Item {

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "The Item is about implementation inheritance, not interface inheritance",
                "Also it is not about inheritance from classes which were designed to be inherited",
                "Inheritance allows a class to reuse the implementation of an existing class.",
                "Inheritance can lead to potential issues when the superclass's implementation changes.",
                "Inheritance can violate encapsulation by exposing the superclass's internals.",
                "Inheritance is not always appropriate for creating reusable software components.",
                "Composition involves using existing classes to build new classes without inheriting their implementation.",
                "Composition promotes encapsulation and allows for greater flexibility and adaptability in design.",
                "Composition allows you to change the behavior of a class at runtime.",
                "Composition makes it easy to switch implementations or modify behavior by replacing components.",
                "Designing classes for inheritance requires careful attention to contracts and documentation.",
                "Favoring composition over inheritance makes code more modular, maintainable, and resistant to changes in external dependencies.",
                "Inheritance should be used judiciously for code reuse when appropriate and results in a clear, understandable design.",
                "Class B should extend class A only if they have is-a relationship (check it by asking " +
                        "'is every B really an A')",
                "For example class Properties was intended to have only 'string' keys, but because of its extending" +
                        "of Hashtable - it can be violated",
                "Inheritance is powerful, but it violates encapsulation"
        );
    }

    @Override
    public String getTheme() {
        return "Favor composition over inheritance";
    }

    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                ForwardingCollection.class, // guava collections
                Stack.class, // Stack extends Vector, but Stack is not Vector
                Properties.class // Properties extends Hashtable, but Properties is not Hashtable
        );
    }

    public void runExamples() {
        // Example of broken functionality because of inheritance
        InstrumentedHashSet<Integer> instrumentedHashSet = new InstrumentedHashSet<>();
        instrumentedHashSet.addAll(List.of(1, 2, 3));
        System.out.println(instrumentedHashSet.addCount);


        // Inheritance example
        Shape square = new Square();
        Shape circle = new Circle();

        // Composition example
        Drawable circleDrawable = new CircleDrawable();
        Drawable squareDrawable = new SquareDrawable();

        ShapeRenderer circleRenderer = new ShapeRenderer(circleDrawable);
        ShapeRenderer squareRenderer = new ShapeRenderer(squareDrawable);

        circleRenderer.render();
        squareRenderer.render();
    }

    // Inheritance example
    public static class Shape {
        // Common shape properties and methods
        private int x;
    }

    public static class Square extends Shape {
        // Square-specific properties and methods
    }

    public static class Circle extends Shape {
        // Circle-specific properties and methods
    }

    // Composition example
    public interface Drawable {
        void draw();
    }

    public static class CircleDrawable implements Drawable {
        @Override
        public void draw() {
            // Draw circle
        }
    }

    public static class SquareDrawable implements Drawable {
        @Override
        public void draw() {
            // Draw square
        }
    }

    public static class ShapeRenderer {
        private Drawable drawable;

        public ShapeRenderer(Drawable drawable) {
            this.drawable = drawable;
        }

        public void render() {
            drawable.draw();
        }
    }

    // Broken - Inappropriate use of inheritance!
    private static class InstrumentedHashSet<E> extends HashSet<E> {
        // The number of attempted element insertions
        private int addCount = 0;
        public InstrumentedHashSet() {
        }
        public InstrumentedHashSet(int initCap, float loadFactor) {
            super(initCap, loadFactor);
        }
        @Override public boolean add(E e) {
            addCount++;
            return super.add(e);
        }
        @Override public boolean addAll(Collection<? extends E> c) {
            addCount += c.size();
            return super.addAll(c);
        }
        public int getAddCount() {
            return addCount;
        }
    }

    // Wrapper class - uses composition in place of inheritance
    private static class InstrumentedSet<E> extends ForwardingSet<E> {
        private int addCount = 0;
        public InstrumentedSet(Set<E> s) {
            super(s);
        }
        @Override public boolean add(E e) {
            addCount++;
            return super.add(e);
        }
        @Override public boolean addAll(Collection<? extends E> c) {
            addCount += c.size();
            return super.addAll(c);
        }
        public int getAddCount() {
            return addCount;
        }
    }

    // Reusable forwarding class | wrapper class | Decorator pattern
    private static class ForwardingSet<E> implements Set<E> {
        private final Set<E> s;
        public ForwardingSet(Set<E> s) { this.s = s; }
        public void clear() { s.clear(); }
        public boolean contains(Object o) { return s.contains(o); }
        public boolean isEmpty() { return s.isEmpty(); }
        public int size() { return s.size(); }
        public Iterator<E> iterator() { return s.iterator(); }
        public boolean add(E e) { return s.add(e); }
        public boolean remove(Object o) { return s.remove(o); }
        public boolean containsAll(Collection<?> c)
        { return s.containsAll(c); }
        public boolean addAll(Collection<? extends E> c)
        { return s.addAll(c); }
        public boolean removeAll(Collection<?> c)
        { return s.removeAll(c); }
        public boolean retainAll(Collection<?> c)
        { return s.retainAll(c); }
        public Object[] toArray() { return s.toArray(); }
        public <T> T[] toArray(T[] a) { return s.toArray(a); }
        @Override public boolean equals(Object o)
        { return s.equals(o); }
        @Override public int hashCode() { return s.hashCode(); }
        @Override public String toString() { return s.toString(); }
    }
}

