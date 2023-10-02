package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AutoService(Item.class)
public class AvoidExcessiveSynchronization implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_11;
    }

    @Override
    public String getTheme() {
        return "Avoid excessive synchronization";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "to avoid liveliness and safety failures, never cede control to the client within a synchronization " +
                        "method or block",
                "for example in code consider implementation of Observable pattern below",
                "the reason is: we are trying to remove an element from a list in the midst of iterating over it",
                """
                        the example with ExecutorService is deadlock; background thread calls s.removeObserver, which
                        attempts to lock 'observers', but it can't acquire the lock, because the main thread already
                        has the lock""",
                "in that example the method 'added' can be called alien for source code, as it is in control of client",
                "to resolve: factor out the alien method from the synchronization blocks",
                "or even better: use concurrent collection, see example at the end",
                "an alien method outside of a synchronized region is known as an open call",
                "as a rule, you should do as little work as possible inside synchronized regions",
                "all the collections in java.util are not thread-safe, except Hashtable and Vector",
                "collections in java.util.concurrent are synchronized internally",
                """
                        in early releases of Java lots of classes were internally synchronized, for example StringBuffer
                        is synchronized internally, so StringBuilder was introduced instead, the same, but without
                        synchronization; the same for Random and ThreadLocalRandom""",
                "in item there are references to the further readings about the techniques how to synchronize a class"
        );
    }

    @Override
    public void runExamples() {
        ObservableSet<Integer> set =
                new ObservableSet<>(new HashSet<>());
        set.addObserver((s, e) -> System.out.println(e));

//        set.addObserver(new SetObserver<Integer>() {
//            @Override
//            public void added(ObservableSet<Integer> set, Integer element) {
//                System.out.println(element);
//                if (element == 23) {
//                    set.removeObserver(this);
//                }
//            }
//        });

        // Observer that uses a background thread needlessly
//        set.addObserver(new SetObserver<>() {
//            public void added(ObservableSet<Integer> s, Integer e) {
//                System.out.println(e);
//                if (e == 23) {
//                    ExecutorService exec =
//                            Executors.newSingleThreadExecutor();
//                    try {
//                        exec.submit(() -> s.removeObserver(this)).get();
//                    } catch (ExecutionException | InterruptedException ex) {
//                        throw new AssertionError(ex);
//                    } finally {
//                        exec.shutdown();
//                    }
//                }
//            }
//        });

        for (int i = 0; i < 100; i++)
            set.add(i);

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    // Broken - invokes alien method from synchronized block!
    public static class ObservableSet<E> extends ForwardingSet<E> {
        public ObservableSet(Set<E> set) { super(set); }
        private final List<SetObserver<E>> observers
                = new ArrayList<>();
        public void addObserver(SetObserver<E> observer) {
            synchronized(observers) {
                observers.add(observer);
            }
        }
        public boolean removeObserver(SetObserver<E> observer) {
            synchronized(observers) {
                return observers.remove(observer);
            }
        }
        private void notifyElementAdded(E element) {
            synchronized(observers) {
                for (SetObserver<E> observer : observers)
                    observer.added(this, element);
            }
        }

        @Override public boolean add(E element) {
            boolean added = super.add(element);
            if (added)
                notifyElementAdded(element);
            return added;
        }
        @Override public boolean addAll(Collection<? extends E> c) {
            boolean result = false;
            for (E element : c)
                result |= add(element); // Calls notifyElementAdded
            return result;
        }
    }

    @FunctionalInterface
    public static interface SetObserver<E> {
        // Invoked when an element is added to the observable set
        void added(ObservableSet<E> set, E element);
    }

    // Reusable forwarding class
    public static class ForwardingSet<E> implements Set<E> {
        private final Set<E> s;
        public ForwardingSet(Set<E> s) { this.s = s; }
        public void clear() { s.clear(); }
        public boolean contains(Object o) { return s.contains(o); }
        public boolean isEmpty() { return s.isEmpty(); }
        public int size() { return s.size(); }
        public Iterator<E> iterator() { return s.iterator(); }
        public boolean add(E e) { return s.add(e); }
        public boolean remove(Object o) { return s.remove(o); }
        public boolean containsAll(Collection<?> c)
        { return s.containsAll(c); }
        public boolean addAll(Collection<? extends E> c)
        { return s.addAll(c); }
        public boolean removeAll(Collection<?> c)
        { return s.removeAll(c); }
        public boolean retainAll(Collection<?> c)
        { return s.retainAll(c); }
        public Object[] toArray() { return s.toArray(); }
        public <T> T[] toArray(T[] a) { return s.toArray(a); }
        @Override public boolean equals(Object o)
        { return s.equals(o); }
        @Override public int hashCode() { return s.hashCode(); }
        @Override public String toString() { return s.toString(); }
    }

//    private void notifyElementAdded(E element) {
//        List<SetObserver<E>> snapshot = null;
//        synchronized(observers) {
//            snapshot = new ArrayList<>(observers);
//        }
//        for (SetObserver<E> observer : snapshot)
//            observer.added(this, element);
//    }

    public static class ObservableSetOk<E> extends ForwardingSet<E> {
        // Thread-safe observable set with CopyOnWriteArrayList
        private final List<SetObserverOk<E>> observers =
                new CopyOnWriteArrayList<>();

        public ObservableSetOk(Set<E> s) {
            super(s);
        }

        public void addObserver(SetObserverOk<E> observer) {
            observers.add(observer);
        }
        public boolean removeObserver(SetObserverOk<E> observer) {
            return observers.remove(observer);
        }
        private void notifyElementAdded(E element) {
            for (SetObserverOk<E> observer : observers)
                observer.added(this, element);
        }
    }

    @FunctionalInterface
    public static interface SetObserverOk<E> {
        // Invoked when an element is added to the observable set
        void added(ObservableSetOk<E> set, E element);
    }
}
