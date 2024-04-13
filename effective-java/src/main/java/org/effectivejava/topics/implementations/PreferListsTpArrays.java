package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class PreferListsTpArrays implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_5;
    }

    @Override
    public String getTheme() {
        return "Prefer lists to arrays";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                """
                        main differences between arrays and lists:
                            - arrays are covariant, generics are invariant;
                            - arrays are reified (meaning arrays know and enforce their element type at runtime),
                                generics are implemented by erasure (meaning that they enforce their type constraints
                                only at compile time and discard (or erase) their element type information ar runtime)""",
                "erasure is what allowed generic types to interoperate freely with legacy code that didn't use generics",
                "none of these are legal: new List<E>[], new List<String>[], new E[] - all will result in generic array " +
                        "creation errors at compile time",
                "it is illegal because it is not typesafe and can violate the fundamental guarantee provided by the " +
                        "generic type system, see example 'why' in 'runExamples'",
                "types such as E, List<E>, List<String> are technically knows as non-reifiable types",
                "non-reifiable type is one whose runtime representation contains less information than its compile-time " +
                        "representation",
                "because of erasure, the only parametrized types that are reifiable are unbounded wildcards types such as " +
                        "List<?>, Map<?,?>, so it is legal to create arrays of unbounded wildcard types"
        );
    }

    @Override
    public void runExamples() {
//        // Fails at runtime!
//        Object[] objectArray = new Long[1];
//        objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

//        // Won't compile!
//        List<Object> ol = new ArrayList<Long>(); // Incompatible types
//        ol.add("I don't fit in");

//        // Why generic array creation is illegal - won't compile!
//        List<String>[] stringLists = new List<String>[1]; // (1)
//        List<Integer> intList = List.of(42); // (2)
//        Object[] objects = stringLists; // (3)
//        objects[0] = intList; // (4)
//        String s = stringLists[0].get(0); // (5)

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Item.super.examplesInCode();
    }
}
