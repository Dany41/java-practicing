package org.practicalunittesting;

import java.util.Objects;

public class Pair<A, B> {
    private final A left;
    private final B right;

    private Pair(A left, B right) {
        this.left = left;
        this.right = right;
    }

    public static <A, B> Pair<A, B> of(A left, B right) {
        return new Pair<>(left, right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
