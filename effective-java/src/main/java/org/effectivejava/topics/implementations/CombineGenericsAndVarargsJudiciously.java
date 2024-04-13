package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

@AutoService(Item.class)
public class CombineGenericsAndVarargsJudiciously implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_5;
    }

    @Override
    public String getTheme() {
        return "Combine generics and varargs judiciously";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "there is a new term 'varargs methods' which is introduced in Item 53; it basically means methods with\n" +
                        "parameters like (Integer... params)",
                "when you invoke a varargs method - an array is created to hold the varargs parameters",
                "so there are some problems when you use it with generic or parametrized types",
                "the usual warning is heap pollution",
                "heap pollution is when a variable of a parametrized type refers to an object that is not of that type\n" +
                        "(see dangerous method below)",
                "so, it basically means that via vararg parameter we can create generic array, like E[], but it is not\n" +
                        "legal to declare explicitly",
                "it is allowed for reason: to use generic types with varargs methods, because it can be very useful in " +
                        "practice",
                "the @SafeVarargs annotation constitutes a promise by the author of a method that it is typesafe, so\n" +
                        "there is no need to suppress warnings",
                "it is unsafe to give another method access to a generic varargs parameter array (like toArray below)"
        );
    }

    @Override
    public void runExamples() {
//        dangerous(List.of("1"), List.of("2")); // throws ClassCastException
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                // class + methods that use varargs and parametrized types, but they are typesafe unlike example below
                // they are typesafe because annotated with @SafeVarargs annotation
                Arrays.class, // asList
                Collections.class, // addAll
                EnumSet.class // of
        );
    }

    // Mixing generics and varargs can violate type safety!
    private static void dangerous(List<String>... stringLists) {
        List<Integer> intList = List.of(42);
        Object[] objects = stringLists;
        objects[0] = intList; // Heap pollution
        String s = stringLists[0].get(0); // ClassCastException
    }

    // UNSAFE - Exposes a reference to its generic parameter array!
    private static <T> T[] toArray(T... args) {
        return args;
    }
}
