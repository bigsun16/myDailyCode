package com.qihui.sun.threadPerMessage;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {
    private final static Random random = new Random(System.currentTimeMillis());
    private final static Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        executor.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(1_000);
                System.out.println("The message " + value + " will be handle by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        /*new Thread(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(1_000);
                System.out.println("The message " + value + " will be handle by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

    public void shutDown() {
        ((ExecutorService) executor).shutdown();
    }
}
