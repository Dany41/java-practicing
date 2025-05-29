package org.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Exercise 2 from <a href="https://www.cl.cam.ac.uk/teaching/1516/ConcDisSys/con-systems-prac.txt">Cambridge site</a>
 */

public class SimpleSynchronizations {

    public static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 1_000_000; j++) {
                i.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 1_000_000; j++) {
                i.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("i = " + i);
    }

}
