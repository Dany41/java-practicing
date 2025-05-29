package org.concurrency;


/**
 * Exercise 3 from <a href="https://www.cl.cam.ac.uk/teaching/1516/ConcDisSys/con-systems-prac.txt">Cambridge site</a>
 */

public class GuardedBlocks {

    public static int i = 0;
    public volatile static boolean done = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 1_000_000; j++) {
                i++;
            }
            done = true;
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            while (!done) {}
            System.out.println(i);
        });
        t2.start();
    }

}
