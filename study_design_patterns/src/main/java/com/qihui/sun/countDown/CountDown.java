package com.qihui.sun.countDown;

public class CountDown {
    private final int total;
    private int counter = 0;

    public CountDown(int total) {
        this.total = total;
    }

    public void down() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (this.counter != this.total) {
                this.wait();
            }
        }
    }
}
