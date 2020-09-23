package com.qihui.sun.twoPhaseTermination;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer extends Thread {
    private final int port;

    private static final int DEFAULT_PORT = 12722;
    private List<ClientHandler> clientHandlers = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private volatile boolean start = true;
    private ServerSocket server;

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
            while (start) {
                Socket client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                executor.submit(handler);
                this.clientHandlers.add(handler);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.disposed();
        }

    }

    private void disposed() {
        for (ClientHandler handler : clientHandlers) {
            handler.stop();
        }
        this.executor.shutdown();
    }

    public void shutDown() throws IOException {
        this.start = false;
        this.interrupt();
        this.server.close();
    }

}
