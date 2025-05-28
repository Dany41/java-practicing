package org.concurrency.helpers;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class Producer implements Runnable {
    private SynchronousQueue<String> q;

    public Producer(SynchronousQueue<String> q) {
        this.q = q;
    }

    public void run() {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        Random random = new Random();

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            try {
                q.put(importantInfo[i]);
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        try {
            q.put("DONE");
        } catch (InterruptedException e) {}
    }
}
