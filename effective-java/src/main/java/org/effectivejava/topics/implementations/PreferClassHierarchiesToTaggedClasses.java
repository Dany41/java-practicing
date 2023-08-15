package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class PreferClassHierarchiesToTaggedClasses implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "Prefer class hierarchies to tagged classes";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "The example of tagged class is below, Figure (with enum [RECTANGLE, CIRCLE])",
                "The example of the same functionality via class hierarchy is given below - class Figure_ClassHierarchy"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Item.super.examplesInCode();
    }

    // Tagged class - vastly inferior to a class hierarchy!
    private static class Figure {
        enum Shape { RECTANGLE, CIRCLE };
        // Tag field - the shape of this figure
        final Shape shape;
        // These fields are used only if shape is RECTANGLE
        double length;
        double width;
        // This field is used only if shape is CIRCLE
        double radius;
        // Constructor for circle
        Figure(double radius) {
            shape = Shape.CIRCLE;
            this.radius = radius;
        }
        // Constructor for rectangle
        Figure(double length, double width) {
            shape = Shape.RECTANGLE;
            this.length = length;
            this.width = width;
        }
        double area() {
            switch(shape) {
                case RECTANGLE:
                    return length * width;
                case CIRCLE:
                    return Math.PI * (radius * radius);
                default:
                    throw new AssertionError(shape);
            }
        }
    }

    // Class hierarchy replacement for a tagged class
    private abstract static class Figure_ClassHierarchy {
        abstract double area();
    }
    private static class Circle extends Figure_ClassHierarchy {
        final double radius;
        Circle(double radius) { this.radius = radius; }
        @Override double area() { return Math.PI * (radius * radius); }
    }

    private static class Rectangle extends Figure_ClassHierarchy {
        final double length;
        final double width;
        Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }
        @Override double area() { return length * width; }
    }
}
