package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;
import java.util.concurrent.*;

@AutoService(Item.class)
public class PreferConcurrencyUtilitiesToWaitAndNotify implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_11;
    }

    @Override
    public String getTheme() {
        return "Prefer concurrency utilities to wait and notify";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "given the difficulty of using wait and notify correctly, you should use the higher-level " +
                        "concurrency utilities instead",
                """
                        higher-level utilities in java.util.concurrent fall into three categories:
                            - Executor Framework
                            - concurrent collections
                            - synchronizers""",
                "it is impossible to exclude concurrent activity from a concurrent collection; locking it will only" +
                        "slow the program",
                "use ConcurrentHashMap in preference to Collections.synchronizedMap, as ConcurrentHashMap is highly " +
                        "optimized",
                "most of ExecutorService implementations use BlockingQueue",
                "synchronizers are objects that enable threads to wait for one another",
                "for example: CountDownLatch, Semaphore, CyclicBarrier and Exchanger, the most powerful one is Phaser",
                "Countdown latches - are single-use barriers that allow one or more threads to wait for one or more " +
                        "other threads to do something",
                "for interval timing, always use System.nanoTime rather than System.currentTimeMillis",
                "always  use the wait loop idiom to invoke the wait method; never invoke it outside of a loop",
                "there is seldom, if ever, a reason to use to use wait and notify in new code"
        );
    }

    @Override
    public void runExamples() {
        try {
            System.out.println("Time elapsed: " + time(Executors.newFixedThreadPool(3), 3,
                    () -> System.out.println(ThreadLocalRandom.current().doubles().limit(10).sum())));
        } catch (InterruptedException ignored) {}

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                BlockingQueue.class
        );
    }

    // Simple framework for timing concurrent execution
    public static long time(Executor executor, int concurrency,
                            Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown(); // Tell timer we're ready
                try {
                    start.await(); // Wait till peers are ready
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown(); // Tell timer we're done
                }
            });
        }
        ready.await(); // Wait for all workers to be ready
        long startNanos = System.nanoTime();
        start.countDown(); // And they're off!
        done.await(); // Wait for all workers to finish
        return System.nanoTime() - startNanos;
    }
}
