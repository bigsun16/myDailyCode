package com.qihui.sun.workerThread;

public class WorkTest {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWork();

        new TransportThread("Zhang San",channel).start();
        new TransportThread("Li Si",channel).start();
        new TransportThread("Wang Wu",channel).start();
    }
}
