package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;
import java.util.Map;

@AutoService(Item.class)
public class FavorTheUseOfStandardFunctionalInterfaces implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_7;
    }

    @Override
    public String getTheme() {
        return "Favor the use of standard functional interfaces";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "LinkedHashMap can be used as cache by overriding its protected method removeEldestEntry",
                "if that method would be written nowadays - it looked be like FI below",
                "java-util.function package provides a large collection of standard functional interfaces",
                "if one of the standard functional interfaces does the job, you should generally use " +
                        "it in preference to a purpose-build functional interface",
                "there are 43 functional interfaces in total, but only 6 are basic ones",
                "basic functional interfaces: UnaryOperator, BinaryOperator, Predicate, Function, Supplier, Consumer",
                "there are three variants of each of the 6 basic ones to operate on the primitive types: int, long, double",
                "there are 9 additional variants of the Function interface",
                "there are two-argument versions of the 3 basic functional interfaces: BiFunction, BiPredicate, BiConsumer",
                "there are 3 relevant BiFunctions for primitive types",
                "there are 3 relevant BiConsumers for primitive types",
                "and the last one - BooleanSupplier, a variant of supplier which return boolean values",
                "don't be tempted to use basic functional interfaces with boxed primitives instead of primitive " +
                        "functional interfaces",
                """
                    write you own only if: there is no standard or:
                        it will be commonly used and could benefit from a descriptive name
                        it has a strong contract associated with it
                        it would benefit from custom default methods""",
                """
                        @FunctionalInterface annotation is similar in spirit to @Override, it serves three purposes:
                            it tells readers of the class and its documentation that the interface was designed to enable lambdas
                            it keeps you honest because the interface won't compile unless it has exactly one abstract method;
                            and it prevents maintainers from accidentally adding abstract methods to the interface as it evolves""",
                "always annotate your functional interfaces with the @FunctionalInterface annotation",
                "don't overload methods which takes functional interfaces as inputs, because there could be problems while invoking"
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

    // Unnecessary functional interface; use a standard one instead. BiPredicate
    @FunctionalInterface
    interface EldestEntryRemovalFunction<K, V> {
        boolean remove(Map<K,V> map, Map.Entry<K,V> eldest);
    }
}
