package org.concurrency;

/**
 * Exercise 1 from <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/QandE/questions.html">Oracle site</a>
 * Answer: add volatile to guarantee publication of 'message' field
 */ 

public class BadThreads {

    static String message;

    private static class CorrectorThread
            extends Thread {

        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {}
            // Key statement 1:
            message = "Mares do eat oats.";
        }
    }

    public static void main(String args[])
            throws InterruptedException {

        (new CorrectorThread()).start();
        message = "Mares do not eat oats.";
        Thread.sleep(2000);
        // Key statement 2:
        System.out.println(message);
    }

}
