package com.qihui.sun.上下文运行模式;

public class ContextTest {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(new ExecutionTask()).start();
        }
    }
}
