package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

@AutoService(Item.class)
public class OverrideClone implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_3;
    }

    @Override
    public String getTheme() {
        return "Override clone judiciously";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "to call clone() method (which comes from the Object class) a class must implement Cloneable interface otherwise exception CloneNotSupportedException will be thrown",
                "but anyway if you a class implements Cloneable you still cannot call clone() method as it is protected",
                "clone creates method without calling a constructor",
                "there is no reason to implement Cloneable if the overridden clone() method doesn't use super.clone()",
                "Cloneable interface changes behavior of the protected method clone() in Object",
                "an overriding method's return type can be subclass of the overridden method's return type",
                "if you implement Cloneable and override the clone() method and just change the type of the returned\n" +
                        "type - you have to write boilerplate with try {} catch() {} - it is mistake by java devs",
                "providing implementation mentioned one line above is a disaster for classes that contain not only primitives + String",
                "the Cloneable architecture is incompatible with normal use of final fields referring to mutable objects\n" +
                        "(except the cases when you can share the immutable fields between objects)",
                """
                    So, to make clients of your code happy, the class that implements Cloneable must:
                        override clone with a public method whose return type is itself
                        the method should first call super.clone, then fix any fields that need fixing
                        there is no need to fix primitive and final fields unless it is serial number or other unique ID
                    """,
                "a better approach to object copying is to provide a copy constructor or copy factory. Main advantage of this approach is\n" +
                        "a copy constructor ot copy factory can take an argument whose type is an interface implemented by the class (see TreeSet(Collection<? extends E> c))",
                "so, the 'good tone' of the clone() method use it ONLY when: all the fields are primitives, OR arrays of primitives\n" +
                        "as clone() works really fast with arrays OTHERWISE create copy constructor or copy factory because the clone() is designed as sh*t"
        );
    }

    @Override
    public void runExamples() {
        CannotBeCloned cannotBeCloned = new CannotBeCloned();
        // cannot be invoked due to protected access
//        cannotBeCloned.clone();
        StillCannotBeCloned stillCannotBeCloned = new StillCannotBeCloned();
//        stillCannotBeCloned.clone();

        try {
            Method cloneMethodFromStillCannotBeCloned = ((Class<Object>)StillCannotBeCloned.class.getGenericSuperclass()).getDeclaredMethod("clone");
            cloneMethodFromStillCannotBeCloned.setAccessible(true);
            StillCannotBeCloned stillCouldBeCloned = (StillCannotBeCloned) cloneMethodFromStillCannotBeCloned.invoke(stillCannotBeCloned);
            System.out.printf("[StillCannotBeCloned] Initial object: %s; cloned one: %s", stillCannotBeCloned, stillCouldBeCloned);
        } catch (Exception e) {
            System.out.println("[StillCannotBeCloned] Still cannot do it even reflectively!!! " + e.getMessage());
        }

        NoNativeCloneNeeded noNativeCloneNeeded = new NoNativeCloneNeeded();
        System.out.printf("[NoNativeCloneNeeded] Initial object: %s; cloned one: %s", noNativeCloneNeeded, noNativeCloneNeeded.clone());

        System.out.println();

        EvenThisCannotBeCloned evenThisCannotBeCloned = new EvenThisCannotBeCloned();
        try {
            System.out.printf("[EvenThisCannotBeCloned] Initial object: %s; cloned one: %s", evenThisCannotBeCloned, evenThisCannotBeCloned.clone());
        } catch (CloneNotSupportedException e) {
            System.out.println("[EvenThisCannotBeCloned] Cloning failed...");
        }

        System.out.println();


        SuccessfullyCloneable successfullyCloneable = new SuccessfullyCloneable();
        try {
            System.out.printf("[SuccessfullyCloneable] Initial object: %s; cloned one: %s", successfullyCloneable, successfullyCloneable.clone());
        } catch (CloneNotSupportedException e) {
            System.out.println("[SuccessfullyCloneable] Cloning failed...");
        }

        System.out.println();

        System.out.println("[BadStack] Find a mistake occurs");
        try {
            BadStack badStack1 = new BadStack();
            badStack1.push("1");
            badStack1.push("2");
            badStack1.push("3");
            BadStack badStack2 = badStack1.clone();
            System.out.println(Integer.parseInt((String) badStack2.pop()));
            System.out.println(Integer.parseInt((String) badStack1.pop()));
        } catch (Exception e) {
            System.out.println("[BadStack] Exception occurred: " + e.getClass());
        }

        System.out.println();

        System.out.println("[GoodStack] Same code, but with GoodStack, no exceptions");
        try {
            GoodStack goodStack1 = new GoodStack();
            goodStack1.push("1");
            goodStack1.push("2");
            goodStack1.push("3");
            GoodStack goodStack2 = goodStack1.clone();
            System.out.println(Integer.parseInt((String) goodStack2.pop()));
            System.out.println(Integer.parseInt((String) goodStack1.pop()));
        } catch (Exception e) {
            System.out.println("[GoodStack] Exception occurred: " + e.getClass());
        }

        System.out.println();

        System.out.println("Imagine, we have the same issue in HashMap, does the following code resolve the problem of cloning?");
        System.out.println("""
                @Override public HashTable clone() {
                    try {
                        HashTable result = (HashTable) super.clone();
                        result.buckets = buckets.clone();
                        return result;
                    } catch (CloneNotSupportedException e) {
                        throw new AssertionError();
                    }
                }""");


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Correct answer: No");
        System.out.println("""
                 @Override public HashTable clone() {
                     try {
                         HashTable result = (HashTable) super.clone();
                         result.buckets = new Entry[buckets.length];
                         for (int i = 0; i < buckets.length; i++)
                         if (buckets[i] != null)
                             result.buckets[i] = buckets[i].deepCopy();
                         return result;
                     } catch (CloneNotSupportedException e) {
                         throw new AssertionError();
                     }
                 }
                 
                 // deepCopy() method in Entry class
                 // Recursively copy the linked list headed by this Entry
                  Entry deepCopy() {
                      return new Entry(key, value,
                        next == null ? null : next.deepCopy());
                  }
                  
                 // improved deepCopy() to avoid stack overflow (as each element in Entry[] requires separate stack frame for deepCopy())
                 // Iteratively copy the linked list headed by this Entry
                 Entry deepCopy() {
                     Entry result = new Entry(key, value, next);
                     for (Entry p = result; p.next != null; p = p.next)
                        p.next = new Entry(p.next.key, p.next.value, p.next.next);
                     return result;
                 }
                """);

    }

    private static class CannotBeCloned {}

    private static class StillCannotBeCloned implements Cloneable {}

    private static class NoNativeCloneNeeded {
        @Override
        protected Object clone() {
            return new NoNativeCloneNeeded();
        }
    }

    private static class EvenThisCannotBeCloned {
        @Override
        public EvenThisCannotBeCloned clone() throws CloneNotSupportedException {
            return (EvenThisCannotBeCloned) super.clone();
        }
    }

    private static class SuccessfullyCloneable implements Cloneable {
        @Override
        public SuccessfullyCloneable clone() throws CloneNotSupportedException {
            return (SuccessfullyCloneable) super.clone();
        }
    }

    public abstract static class Stack {
        protected Object[] elements;
        protected int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;
        public Stack() {
            this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }
        public void push(Object e) {
            ensureCapacity();
            elements[size++] = e;
        }
        public Object pop() {
            if (size == 0)
                throw new EmptyStackException();
            Object result = elements[--size];
            elements[size] = null; // Eliminate obsolete reference
            return result;
        }
        // Ensure space for at least one more element.
        private void ensureCapacity() {
            if (elements.length == size)
                elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public static class BadStack extends Stack implements Cloneable {
        @Override
        public BadStack clone() {
            try {
                return (BadStack) super.clone();
            } catch (CloneNotSupportedException ex) {
                System.out.println("[BadStack] Something went wrong");
                return null;
            }
        }
    }

    public static class GoodStack extends Stack implements Cloneable {
        @Override
        public GoodStack clone() {
            try {
                GoodStack result = (GoodStack) super.clone();
                result.elements = elements.clone();
                return result;
            } catch (CloneNotSupportedException ex) {
                System.out.println("[GoodStack] Something went wrong");
                return null;
            }
        }

        public GoodStack() {
            super();
        }

        // copy constructor
        public GoodStack(GoodStack goodStack) {
            this.elements = goodStack.elements.clone();
            this.size = goodStack.size;
        }

        // factory method
        public static GoodStack newInstance(GoodStack goodStack) {
            GoodStack result = new GoodStack();
            result.elements = goodStack.elements.clone();
            result.size = goodStack.size;
            return result;
        }


    }


}
