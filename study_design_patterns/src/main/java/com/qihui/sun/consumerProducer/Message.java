package com.qihui.sun.consumerProducer;

public class Message {
    private final String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
