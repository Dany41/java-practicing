package org.fp.collections;

import java.util.Arrays;

public sealed interface FunctionalList<T> permits Nil, Cons {

    static <T> FunctionalList<T> create(T... elements) {
        return elements.length == 0
                ? Nil.getInstance()
                : new Cons<>(elements[0], create(Arrays.copyOfRange(elements, 1, elements.length)));
    }

}
