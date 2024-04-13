package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class AdhereToGenerallyAcceptedNamingConventions implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Adhere to generally accepted naming conventions";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "there are two categories of naming conventions: typographical and grammatical",
                "typographical is about module, packages, classes, interface, method, field namings",
                "type parameters naming conventions: T for an arbitrary type, E for the element type of a collection, " +
                        "K and V for the key types of a map, and X for an exception, return type is R",
                "a sequence of arbitrary types can be T, U, V or T1, T2, T3",
                """
                        typographical conventions:
                        Identifier Type | Examples
                        package or module -> org.junit.jupiter.api; com.google.common.collect
                        class or interface -> Stream, FutureTask, LinkedHashMap, HttpClient
                        method or field -> remove, groupingBy, getCrc
                        constant field -> MIN_VALUE, NEGATIVE_INFINITY
                        local variable -> i, denom, houseNum
                        type parameter -> T, E, K, V, X, R, U, T1, T2
                        """,
                "grammatical naming conventions are more flexible and more controversial than typographical conventions",
                "instantiable classes are generally named with a singular noun, such as Thread; non-instantiable " +
                        "classes are often named with a plural form, for example: Collections",
                "methods that convert type to another types are named like to[Type], for example: toList, toString",
                "methods that allow you to view an object like another type (meaning view pattern) are named like " +
                        "as[Type], for example: asList.",
                "the quote from The Java Language Specification [JLS, 6.1], \"These conventions should not be followed " +
                        "slavishly if long-held conventional usage dictates otherwise\". Use common sense"
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
}
