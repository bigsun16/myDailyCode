package com.qihui.sun.util.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 设置同时执行线程数，线程超过指定数字会等待，
 */
public class TestSemaphore {
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" doing something...");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+" done...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },"num-"+i).start();
        }
    }
}
