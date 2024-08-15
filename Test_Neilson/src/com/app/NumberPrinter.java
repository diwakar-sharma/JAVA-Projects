package com.app;
public class NumberPrinter {

    private static final int REPEAT_COUNT = 20;
    private static final Object lock = new Object();
    private static boolean isOneTurn = true;

    public static void main(String[] args) {
        // Thread for printing number 1
        Thread printOneThread = new Thread(new Runnable() {
            @Override
            public void run() {
                printOne();
            }
        });

        // Thread for printing number 2
        Thread printTwoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                printTwo();
            }
        });

        // Start the threads
        printOneThread.start();
        printTwoThread.start();

        // Wait for both threads to finish
        try {
            printOneThread.join();
            printTwoThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFinished printing 1 2 sequence.");
    }

    private static void printOne() {
        for (int i = 0; i < REPEAT_COUNT; i++) {
            synchronized (lock) {
                while (!isOneTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.print("1 ");
                isOneTurn = false; // Set turn to two
                lock.notify(); // Notify the other thread
            }
        }
    }

    private static void printTwo() {
        for (int i = 0; i < REPEAT_COUNT; i++) {
            synchronized (lock) {
                while (isOneTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.print("2 ");
                isOneTurn = true; // Set turn to one
                lock.notify(); // Notify the other thread
            }
        }
    }
}
