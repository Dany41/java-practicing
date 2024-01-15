package org.fp.collections;

import lombok.Getter;

import java.util.function.Function;
import java.util.function.Predicate;

public final class Nil implements FunctionalList {

    @Getter
    private static final Nil instance = new Nil();

    private Nil() {}

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public FunctionalList filter(Predicate fun) {
        return this;
    }

    @Override
    public FunctionalList flatMap(Function fun) {
        return this;
    }

    @Override
    public FunctionalList map(Function fun) {
        return this;
    }
}
