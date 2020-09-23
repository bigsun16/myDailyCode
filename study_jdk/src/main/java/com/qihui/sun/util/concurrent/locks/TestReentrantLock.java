package com.qihui.sun.util.concurrent.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 构造函数的参数是指定是否设置公平锁，true就是公平锁，会按照排队顺序获取执行权
 */
public class TestReentrantLock {
    private static Lock lock = new ReentrantLock(true);
    private static final int threadNum = 10;
    private static CountDownLatch latch = new CountDownLatch(threadNum);
    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int y = 0; y < 1000; y++) {
                    lock.lock();
                    num++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                    lock.unlock();
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(num);
    }

}
