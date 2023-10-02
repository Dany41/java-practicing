package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class DocumentThreadSafety implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_11;
    }

    @Override
    public String getTheme() {
        return "Document thread safety";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "Javadoc does not include the synchronized modifier in its output. The presence of the synchronized " +
                        "modifier in a method declaration is an implementation detail, not a part of its API",
                "also synchronized keyword doe not reliably indicates that the method is thread-safe",
                "to enable safe concurrent use, a class must clearly document what level of thread safety it supports",
                """
                        there is a list of thread safety's levels, it is not full, but common one:
                            - immutable (String, Long, BigInteger)
                            - unconditionally thread-safe - instances of the class are mutable, but the class has
                                sufficient internal synchronization (AtomicLong, ConcurrentHashMap)
                            - conditionally thread-safe - like unconditionally thread-safe, except that some methods
                                require external synchronization for safe concurrent use (Collector.synchronized
                                wrappers, whose iterators require synchronization)
                            - not thread-safe (ArrayList, HashMap)
                            - thread-hostile - this class is unsafe for concurrent use even if every method invocation
                                is surrounded by external synchronization""",
                "lock fields are used to prevent denial-of-service attack (holding publicly accessible lock for a " +
                        "prolonged time)",
                "lock fields should always be declared final",
                "locks can be primitive (hand made) or from java.util.concurrent.locks package",
                "every class should clearly document its thread safety properties with a carefully worded prose " +
                        "description or a thread safety annotation (see, for example, ThreadSafe annotation)"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
