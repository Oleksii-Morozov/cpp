package dev.alerix.thread;

import dev.alerix.service.Operations;

import java.util.Random;

public class MyRunnable implements Runnable {
    private final Operations operations;
    private static final Random random = new Random();

    public MyRunnable(Operations operations) {
        this.operations = operations;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            makeSomeNoise();
            try {
                //noinspection BusyWait
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private void makeSomeNoise() {
        System.out.println("Hello, world!");
    }
}
