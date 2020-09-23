package com.qihui.sun.util.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 构造函数的参数指定预计涉及的线程数量
 * latch.countDown()会使指定数量减一，当该值为0时latch.await()之后的代码才会执行
 */
public class TestCountDownLatch {
    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) {
        new Thread(()->{
            try {
                latch.await();
                System.out.println("thread 1 start...");
                Thread.sleep(2000);
                System.out.println("thread 1 execute end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                System.out.println("thread 2 start...");
                Thread.sleep(7000);
                System.out.println("thread 2 execute end...");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                latch.await();
                System.out.println("thread 3 start...");
                Thread.sleep(5000);
                System.out.println("thread 3 execute end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                System.out.println("thread 4 start...");
                Thread.sleep(3000);
                System.out.println("thread 4 execute end...");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
