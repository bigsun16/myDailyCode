package com.qihui.sun.util.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class TestAtomicArray {
    private static final int threadCount = 1000;
    private static final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
    private static final AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
//    private static final int[] testArray = new int[10];

    private static class Task implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 10; j++) {
                    atomicIntegerArray.addAndGet(j,1);
                }
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Task()).start();
        }
        countDownLatch.await();
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.print(atomicIntegerArray.get(i)+", ");
        }
    }
}
