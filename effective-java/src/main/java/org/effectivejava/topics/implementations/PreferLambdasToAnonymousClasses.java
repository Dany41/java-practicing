package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import com.google.common.collect.Lists;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

@AutoService(Item.class)
public class PreferLambdasToAnonymousClasses implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_7;
    }

    @Override
    public String getTheme() {
        return "Prefer lambdas to anonymous classes";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "historically, interfaces, or abstract classes, with a single method were known as function types, its " +
                    "instances known as function objects",
            "comparator anonymous class instance below represents abstract strategy pattern; the class itself is " +
                    "a concrete strategy",
            """
                in java 8, the language formalized the notion of interfaces, or abstract classes, with a single method
                and provided special treatment for them. Interfaces are now known as functional interfaces, and the
                language allows you to create instances of these interfaces using lambda expressions, or lambdas""",
            "compiler deduces the types for lambdas from context, using type inference (cases where you need to " +
                    "explicitly write them are described in JLS, chapter 18)",
            "omit the types of all lambda parameters unless their presence makes your program clearer",
            "below you can find Operation enums written via lambdas",
            "lambdas lack names and documentation; if a computation isn't self-explanatory, or exceeds a few lines, don't " +
                    "put it in a lambda",
            "in the lambda - a word 'this' refers to enclosing object, in anonymous classes - to itself",
            "don't use anonymous classes for function objects unless you have to create instances of type that aren't " +
                    "functional interfaces"
        );
    }

    @Override
    public void runExamples() {
        List<String> words = Lists.newArrayList("a", "b", "c", "aa");
        // Anonymous class instance as a function object - obsolete!
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        // Lambda expression as function object (replaces anonymous class)
        Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        // Using comparator construction method
        Collections.sort(words, Comparator.comparingInt(String::length));

        // Even shorter
        words.sort(Comparator.comparingInt(String::length));
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    // Enum with function object fields & constant-specific behavior
    public enum Operation {
        PLUS("+", (x, y) -> x + y),
        MINUS("-", (x, y) -> x - y),
        TIMES("*", (x, y) -> x * y),
        DIVIDE("/", (x, y) -> x / y);
        private final String symbol;
        private final DoubleBinaryOperator op;

        Operation(String symbol, DoubleBinaryOperator op) {
            this.symbol = symbol;
            this.op = op;
        }

        @Override
        public String toString() {
            return symbol;
        }

        public double apply(double x, double y) {
            return op.applyAsDouble(x, y);
        }
    }
}
