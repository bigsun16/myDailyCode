package com.qihui.sun.twoPhaseTermination;

import java.io.IOException;

public class AppServerTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer server = new AppServer(13345);
        server.start();
        Thread.sleep(10_000);
        server.shutDown();
    }
}
