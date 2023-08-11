package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class CompositionOverInheritance implements Item {

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "The Item is about implementation inheritance, not interface inheritance",
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
                "Inheritance should be used judiciously for code reuse when appropriate and results in a clear, understandable design."
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

    public void runExamples() {
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
}

