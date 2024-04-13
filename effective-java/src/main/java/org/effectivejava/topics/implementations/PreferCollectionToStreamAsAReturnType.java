package org.effectivejava.topics.implementations;

import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PreferCollectionToStreamAsAReturnType implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_7;
    }

    @Override
    public String getTheme() {
        return "Prefer Collection to Stream as a return type";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "Stream does not extends Iterable, so you can't use it in for-each loop",
            "Collection or an appropriate subtype is generally the best return type for a public," +
                    "sequence returning method",
            "do not store a large sequence in memory just to return it as a collection (for example below PowerSet)"
        );
    }

    @Override
    public void runExamples() {
        // Won't compile, due to limitations on Java's type inference
//        for (ProcessHandle ph : ProcessHandle.allProcesses()::iterator) {
            // Process the process
//        }

        // Hideous workaround to iterate over a stream
        for (ProcessHandle ph : (Iterable<ProcessHandle>)
                ProcessHandle.allProcesses()::iterator) {

        }

        // Using adapter method
        for (ProcessHandle p : iterableOf(ProcessHandle.allProcesses())) {
            // Process the process
        }


    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    // Adapter from Stream<E> to Iterable<E>
    private static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }

    // Adapter from Iterable<E> to Stream<E>
    private static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    private static class PowerSet {
        public static final <E> Collection<Set<E>> of(Set<E> s) {
            List<E> src = new ArrayList<>(s);
            if (src.size() > 30) {
                throw new IllegalArgumentException("Set too big " + s);
            }
            return new AbstractList<Set<E>>() {
                @Override
                public int size() {
                    return 1 << src.size(); // 2 to the power src size
                }

                @Override
                public boolean contains(Object o) {
                    return o instanceof Set && src.containsAll((Set)o);
                }

                @Override
                public Set<E> get(int index) {
                    Set<E> result = new HashSet<>();
                    for (int i = 0; index != 0; i++, index >>= 1) {
                        if ((index & 1) == 1) {
                            result.add(src.get(i));
                        }
                    }
                    return result;
                }
            };
        }
    }
}
