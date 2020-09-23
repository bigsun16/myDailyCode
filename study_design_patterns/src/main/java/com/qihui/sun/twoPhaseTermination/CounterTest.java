package com.qihui.sun.twoPhaseTermination;

public class CounterTest {
    public static void main(String[] args) {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counterIncrement.close();
    }
}
