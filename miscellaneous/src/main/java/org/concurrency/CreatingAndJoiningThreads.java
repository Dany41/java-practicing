package org.concurrency;

/**
 * Exercise 1 from <a href="https://www.cl.cam.ac.uk/teaching/1516/ConcDisSys/con-systems-prac.txt">Cambridge site</a>
 */

public class CreatingAndJoiningThreads {
    public static void main(String[] args) {
        // 1.1
        new Thread(() -> System.out.println("Hello world!")).start();

        // 1.2
        for (int i = 0; i < 5; i++) {
            new Thread(() -> System.out.println("Hello world!")).start();
        }

        // 1.3
        for (int i = 0; i < 5; i++) {
            new Thread(() -> System.out.println(Thread.currentThread().getName() + ": Hello world!")).start();
        }
    }
}
