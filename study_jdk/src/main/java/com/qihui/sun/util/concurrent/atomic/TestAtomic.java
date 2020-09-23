package com.qihui.sun.util.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {
    public static AtomicInteger num = new AtomicInteger(0);
    public static int count = 0;
    public static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {
        testAtomicInteger();
    }
    private static void testAtomicInteger() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (int y = 0; y < 1000; y++) {
                    num.incrementAndGet();
                    count++;
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(num.get());
        System.out.println(count);
    }
}
