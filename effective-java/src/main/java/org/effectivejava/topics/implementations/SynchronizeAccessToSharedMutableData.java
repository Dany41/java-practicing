package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

@AutoService(Item.class)
public class SynchronizeAccessToSharedMutableData implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_11;
    }

    @Override
    public String getTheme() {
        return "Synchronize access to shared mutable data";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "synchronization is required for reliable communication between threads as well as for mutual exclusion",
                "mutual exclusion - no thread can access an object in an inconsistent state",
                "do not use Thread.stop (deprecated and can lead to data corruption)",
                "look at example below with a thread",
                "the VM can optimize that loop into {if (!boolean) { while (true) }}; such optimizations are known " +
                        "as hoisting",
                "synchronization is not guaranteed to work unless both read and write operations are synchronized",
                "volatile performs mutual exclusion, it guarantees that any thread that reads the field wil see " +
                        "the most recently written value",
                "but be careful with volatile, look at nextSerialNumber below",
                "the problem there is in '++' operator - it is not atomic, it performs two operations on the " +
                        "nextSerialNumber field: reads, then writes; in this case you should use synchronization",
                "the best advice to avoid all of those problem is: confine mutable data to a single thread",
                "when multiple threads share mutable data, each thread that read or writes the data must " +
                        "perform synchronization"
        );
    }

    private static boolean stopRequested;

    @Override
    public void runExamples() {
        // Question: how long the following code will work?
//        Thread backgroundThread = new Thread(() -> {
//            int i = 0;
//            while (!stopRequested)
//                i++;
//            System.out.println(i);
//        });
//        backgroundThread.start();
//        sleep(1);
//        stopRequested = true;
//        System.out.println("set to true");
//        joinThread(backgroundThread);
        // how to fix?
    }

    private void joinThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException ignored) {}
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ignored) {}
    }

    private static synchronized void requestStop() {
        stopRequested = true;
    }
    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    // Broken - requires synchronization!
    private static volatile int nextSerialNumber = 0;
    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
