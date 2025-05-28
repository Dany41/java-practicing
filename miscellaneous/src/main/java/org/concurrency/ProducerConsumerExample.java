package org.concurrency;

import org.concurrency.helpers.Consumer;
import org.concurrency.helpers.Producer;

import java.util.concurrent.SynchronousQueue;

/**
 * Exercise 2 from <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/QandE/questions.html">Oracle site</a>
 * Answer: use SynchronousQueue instead of Drop
 */

public class ProducerConsumerExample {
    public static void main(String[] args) {
//        Drop drop = new Drop();
        SynchronousQueue<String> q = new SynchronousQueue<>();
        (new Thread(new Producer(q))).start();
        (new Thread(new Consumer(q))).start();
    }
}
