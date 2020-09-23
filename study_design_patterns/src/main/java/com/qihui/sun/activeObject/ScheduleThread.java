package com.qihui.sun.activeObject;

public class ScheduleThread extends Thread {

    private final ActivationQueue activationQueue;

    public ScheduleThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest request) {
        this.activationQueue.put(request);
    }

    @Override
    public void run() {
        while (true) {
            this.activationQueue.take().execute();
        }
    }
}
