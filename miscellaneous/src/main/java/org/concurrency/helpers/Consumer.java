package org.concurrency.helpers;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class Consumer implements Runnable {
    private SynchronousQueue<String> q;

    public Consumer(SynchronousQueue<String> q) {
        this.q = q;
    }

    public void run() {
        Random random = new Random();
        try {
            for (String message = q.take(); ! message.equals("DONE"); message = q.take()) {
                System.out.format("MESSAGE RECEIVED: %s%n", message);
                Thread.sleep(random.nextInt(5000));
            }
        } catch (InterruptedException e) {}
    }
}
