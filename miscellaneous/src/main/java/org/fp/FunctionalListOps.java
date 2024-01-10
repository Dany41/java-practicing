package org.fp;

import org.fp.collections.*;
import org.fp.collections.FunctionalList;

public class FunctionalListOps {

    public static void main(String[] args) {
        FunctionalList<Integer> list = FunctionalList.create(1, 2, 3, 4);
        FunctionalList<Double> doubleList = FunctionalList.create(1., 2., 3., 4.);

        System.out.println(FunctionalListOps.sum(list));
        System.out.println(FunctionalListOps.product(doubleList));
    }

    public static int sum(FunctionalList<Integer> ints) {
            return switch (ints) {
                case Nil ignored -> 0;
                case Cons<Integer>(Integer head, FunctionalList<Integer> tail) -> head + sum(tail);
            };
    }

    public static double product(FunctionalList<Double> doubles) {
            return switch (doubles) {
                case Nil ignored -> 0.0;
                case Cons<Double>(Double head, FunctionalList<Double> ignored) when head == 0.0 -> 0.0;
                case Cons<Double>(Double head, FunctionalList<Double> tail) -> head * product(tail);
            };
    }

}
