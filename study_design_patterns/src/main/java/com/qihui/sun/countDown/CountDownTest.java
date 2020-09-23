package com.qihui.sun.countDown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownTest {
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        final CountDown latch = new CountDown(5);
        System.out.println("准备多线程处理任务");

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is working...");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.down();
            }, String.valueOf(i)).start();
        }
        latch.await();
        System.out.println("多线程任务全部结束，准备第二阶段任务");
        System.out.println("++++++++++++++++");
        System.out.println("FINISHED...");
    }
}
