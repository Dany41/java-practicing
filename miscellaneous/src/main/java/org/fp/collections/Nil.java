package org.fp.collections;

import lombok.Getter;

public final class Nil implements FunctionalList {

    @Getter
    private static final Nil instance = new Nil();

    private Nil() {}

}
