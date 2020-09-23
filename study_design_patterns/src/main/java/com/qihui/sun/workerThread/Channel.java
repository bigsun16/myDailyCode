package com.qihui.sun.workerThread;

import java.util.Arrays;

public class Channel {
    private static final int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private final WorkerThread[] workerPool;
    private int head;
    private int tail;
    private int count;

    public Channel(int workers) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.workerPool = new WorkerThread[workers];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.init();
    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    public void startWork() {
        for (WorkerThread workerThread : Arrays.asList(workerPool)) {
            workerThread.start();
        }
    }

    public synchronized void put(Request request) {
        while (count >= requestQueue.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        this.requestQueue[tail] = request;
        this.tail = (tail + 1) % requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take() {
        while (this.count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = this.requestQueue[head];
        this.head = (this.head + 1) % this.requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }
}
