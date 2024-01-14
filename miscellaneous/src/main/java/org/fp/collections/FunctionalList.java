package org.fp.collections;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface FunctionalList<T> permits Nil, Cons {

    static <T> FunctionalList<T> create(T... elements) {
        return elements.length == 0
                ? Nil.getInstance()
                : new Cons<>(elements[0], create(Arrays.copyOfRange(elements, 1, elements.length)));
    }

    boolean isEmpty();

    <R> FunctionalList<R> map(Function<T, R> fun);

    FunctionalList<T> filter(Predicate<T> fun);

    <R> FunctionalList<R> flatMap(Function<T, FunctionalList<R>> fun);

}
