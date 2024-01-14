package org.fp;

import org.fp.collections.*;
import org.fp.collections.FunctionalList;

import java.util.function.BiFunction;

public class FunctionalListOps {

    public static void main(String[] args) {
        FunctionalList<Integer> list = FunctionalList.create(1, 2, 3, 4);
        FunctionalList<Double> doubleList = FunctionalList.create(1., 2., 3., 4.);

        System.out.println(list.map(i -> i * 5));
    }

    public static int sum(FunctionalList<Integer> ints) {
        return foldLeft(ints, 0, Integer::sum);
    }

    public static <T> FunctionalList<T> append(FunctionalList<T> list1, FunctionalList<T> list2) {
        return switch (list1) {
            case Cons(var head, var tail) -> new Cons<>(head, FunctionalListOps.append(tail, list2));
            case Nil nil -> list2;
        };
    }

    public static <T, R> R foldLeft(FunctionalList<T> list, R acc, BiFunction<T, R, R> fun) {
        return switch (list) {
            case Nil ignored -> acc;
            case Cons(var head, var tail) -> foldLeft(tail, fun.apply(head, acc), fun);
        };
    }

    public static <T, R> R foldRight(FunctionalList<T> list, R acc, BiFunction<T, R, R> fun) {
        return foldLeft(FunctionalListOps.reverse(list), acc, fun);
    }

    private static <T> FunctionalList<T> reverse(FunctionalList<T> list) {
        return foldLeft(list, FunctionalList.create(), (element, l) -> new Cons(element, l));
    }

    public static <T> FunctionalList<T> setHead(T head, FunctionalList<Integer> list) {
            return switch (list) {
                case Cons cons -> new Cons(head, cons);
                case Nil nil -> FunctionalList.create(head);
            };
    }

    public static double product(FunctionalList<Double> doubles) {
            return foldLeft(doubles, 1.0, (e, a) -> e * a);
    }

}
