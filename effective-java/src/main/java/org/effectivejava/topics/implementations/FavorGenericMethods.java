package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

import static com.google.common.collect.Sets.union;

@AutoService(Item.class)
public class FavorGenericMethods implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_5;
    }

    @Override
    public String getTheme() {
        return "Favor generic methods";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "the type parameter list, which declares the type parameters, goes between a method's modifiers and its\n" +
                        "return type",
                """
                        you can use a single object for all required type parametrization, but you need to write a static factory
                        method to repeatedly dole out the object for each requested type parametrization. This pattern is
                        called generic singleton factory""", // look examples in code
                "as an exercise - create analog of Function.identity()",
                "generics methods, like generic types, are safer and easier to use than methods requiring their clients\n" +
                        "to put explicit casts on input parameters and return values",
                "also the 'bullet points' from Favor generic methods can be applied here"
        );
    }

    @Override
    public void runExamples() {
        Set<String> guys = Set.of("Tom", "Dick", "Harry");
        Set<String> stooges = Set.of("Larry", "Moe", "Curly");
        Set<String> aflCio = union(guys, stooges);
        System.out.println(aflCio);

        // identityFunction works fine
        String[] strings = { "jute", "hemp", "nylon" };
        UnaryOperator<String> sameString = identityFunction();
        for (String s : strings)
            System.out.println(sameString.apply(s));
        Number[] numbers = { 1, 2.0, 3L };
        UnaryOperator<Number> sameNumber = identityFunction();
        for (Number n : numbers)
            System.out.println(sameNumber.apply(n));
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                Collections.class // reverseOrder(), emptySet()
        );
    }









    // Generic singleton factory pattern
    private static final UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }
}
