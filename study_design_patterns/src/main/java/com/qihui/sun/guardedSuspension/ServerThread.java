package com.qihui.sun.guardedSuspension;

import java.util.Random;

public class ServerThread extends Thread {

    private final RequestQueue queue;
    private final Random random;
    private volatile boolean isClosed = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while(!isClosed){
            Request request = queue.getRequest();
            if (request==null){
                System.out.println("received empty queue...");
                continue;
            }
            System.out.println("Server -> "+ request.getValue());
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close(){
        this.isClosed = true;
        this.interrupt();
    }
}
