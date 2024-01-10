package org.fp.collections;

public record Cons<T>(T head, FunctionalList<T> tail) implements FunctionalList<T> { }
