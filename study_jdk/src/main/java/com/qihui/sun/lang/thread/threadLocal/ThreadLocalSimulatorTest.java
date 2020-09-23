package com.qihui.sun.lang.thread.threadLocal;

import java.util.Random;

public class ThreadLocalSimulatorTest {
    private static final ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator<String>(){
        @Override
        public String initialValue() {
            return "no value";
        }
    };

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread_1");
            try {
                Thread.sleep(random.nextInt(1_000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread_2");
            try {
                Thread.sleep(random.nextInt(1_000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}
