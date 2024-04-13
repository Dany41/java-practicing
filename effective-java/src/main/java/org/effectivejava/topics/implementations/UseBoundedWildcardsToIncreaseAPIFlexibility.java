package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.Collection;
import java.util.List;

@AutoService(Item.class)
public class UseBoundedWildcardsToIncreaseAPIFlexibility implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_5;
    }

    @Override
    public String getTheme() {
        return "Use bounded wildcards to increase API flexibility";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "every type is a subtype and a supertype of itself",
                "wildcards can solve invariant problems (make act like covariant/contravariant) (see interface Stack below)",
                "use wildcard types on input parameters that represent producers and consumers for maximum flexibility",
                "if an input parameter is both a producer and consumer, then wildcard types will not help",
                "mnemonic to remember: PECS - producer-extends, consumer-super",
                "do not use bounded wildcard types as return types",
                "if the user of a class has to think about wildcard types, there is probably something wrong with its API",
                "compiler prior to java 8 will not compile some code with wildcards because it will not be able to infer\n" +
                        "types correctly (see example with Set in book)",
                "pointing the type like this: Union.<Number>union() is called explicit type argument (JLS, 15.12)",
                "comparable is always consumer, so the right way to extend Comparable is 'extends Comparable<? super T>';\n" +
                        "the same is true about Comparator",
                "if a type parameter appears only once in a method declaration, replace it with wildcard (see exercise\n" +
                        "'swap' in bobocode project)"
        );
    }

    @Override
    public void runExamples() {
        // relates to invariant <-> covariant example
//        Stack<Number> numberStack = new Stack<>();
//        Iterable<Integer> integers = ... ;
//        numberStack.pushAll(integers);

        // relates to invariant <-> contravariant example
//        Stack<Number> numberStack = new Stack<Number>();
//        Collection<Object> objects = ... ;
//        numberStack.popAll(objects);
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Item.super.examplesInCode();
    }

    public interface Stack<E> {
        void push(E e);
        E pop();
        boolean isEmpty();
        // will throw incompatible types error
//        default void pushAll(Iterable<E> src) {
//            for (E e : src)
//                push(e);
//        }
        // solved the problem above
        default void pushAll(Iterable<? extends E> src) {
            for (E e : src)
                push(e);
        }

        // popAll method without wildcard type - deficient!
//        default void popAll(Collection<E> dst) {
//            while (!isEmpty())
//                dst.add(pop());
//        }
        //
        default void popAll(Collection<? super E> dst) {
            while (!isEmpty())
                dst.add(pop());
        }
    }
}
