package com.qihui.sun.guardedSuspension;

public class SuspensionTest {

    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue,"Alice").start();
        ServerThread server = new ServerThread(queue);
        server.start();
        Thread.sleep(15_000);
        server.close();
    }
}
