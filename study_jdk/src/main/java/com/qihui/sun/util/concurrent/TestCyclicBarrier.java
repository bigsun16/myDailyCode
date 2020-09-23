package com.qihui.sun.util.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 构造函数第一个参数是指定等待线程数量，当一个线程执行到cyclicBarrier.await()时会等待，指定数量的全部线程都执行到cyclicBarrier.await()时才继续
 * 第二个参数是回调函数，当指定数量所有线程都执行到cyclicBarrier.await()时会被调用
 */
public class TestCyclicBarrier {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("all thread were ending..."));

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(()->{
            try {
                System.out.println("thread 1 start...");
                Thread.sleep(4000);
                System.out.println("thread 1 end...");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                System.out.println("thread 2 start...");
                Thread.sleep(2000);
                System.out.println("thread 2 end...");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        cyclicBarrier.await();
        System.out.println("main thread ending...");
    }
}
