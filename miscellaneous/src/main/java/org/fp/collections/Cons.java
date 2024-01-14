package org.fp.collections;

import org.fp.FunctionalListOps;

import java.util.function.Function;
import java.util.function.Predicate;

public record Cons<T>(T head, FunctionalList<T> tail) implements FunctionalList<T> {
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public <R> FunctionalList<R> map(Function<T, R> fun) {
        return this.flatMap(e -> new Cons<>(fun.apply(e), Nil.getInstance()));
    }

    @Override
    public FunctionalList<T> filter(Predicate<T> fun) {
        return fun.test(head) ? new Cons<>(head, tail.filter(fun)) : tail.filter(fun);
    }

    @Override
    public <R> FunctionalList<R> flatMap(Function<T, FunctionalList<R>> fun) {
        return FunctionalListOps.foldLeft(this, FunctionalList.create(), (e, l) -> FunctionalListOps.append(l, fun.apply(e)));
    }
}
