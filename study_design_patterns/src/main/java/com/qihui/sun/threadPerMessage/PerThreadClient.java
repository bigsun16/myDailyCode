package com.qihui.sun.threadPerMessage;

public class PerThreadClient {
    public static void main(String[] args) {
        final MessageHandler handler = new MessageHandler();
        for (int i = 0; i < 10; i++) {
            handler.request(new Message(String.valueOf(i)));
        }
        handler.shutDown();
    }
}
