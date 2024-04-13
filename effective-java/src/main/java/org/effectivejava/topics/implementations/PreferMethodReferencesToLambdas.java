package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;

@AutoService(Item.class)
public class PreferMethodReferencesToLambdas implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_7;
    }

    @Override
    public String getTheme() {
        return "Prefer method references to lambdas";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "method references are even shorter than lambdas",
                """
                        There is nothing you can do with a method reference that you can't also do with a lambda (with
                        one exception - JLS, 9.9-2)
                        For example if method has signature <F extends Exception> ()->String throws F
                        """,
                """
                        There are 5 method reference types:
                            Static
                            Bound
                            Unbound
                            Class Constructor
                            Array Constructor""",
                "where method references are shorter and clearer, use them; where they aren't, " +
                        "stick with lambdas",
                "" // todo: show diff in bytecode
        );
    }

    @Override
    public void runExamples() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        map.merge(1, 4, Integer::sum);
        map.merge(2, 3, Integer::sum);
        map.merge(3, 2, Integer::sum);
        map.merge(4, 5, Integer::sum);
        System.out.println(map);

        System.out.println();

        System.out.println("Static");
        System.out.println("Integer::parseInt | str -> Integer.parseInt(str)");
        Function<String, Integer> staticMethodRef = Integer::parseInt;
        System.out.println("Bound");
        System.out.println("Instant.now()::isAfter | Instant then = Instant.now(); t -> then.isAfter(t)");
        Function<Instant, Boolean> boundMethodRef = Instant.now()::isAfter;
        System.out.println("Unbound");
        System.out.println("String::toLowerCase() | str -> str.toLowerCase()");
        Function<String, String> unboundMethodRef = String::toLowerCase;
        System.out.println("Class Constructor");
        System.out.println("TreeMap<K,V>::new | () -> new TreeMap<K, V>)");
        Supplier<TreeMap<Integer, String>> classConstructorMethodRef = TreeMap::new;
        System.out.println("Array Constructor");
        System.out.println("int[]::new | len -> new int[len]");
        Function<Integer, int[]> arrayConstructorMethodRef = int[]::new;
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
