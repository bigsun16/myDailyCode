package com.qihui.sun.future;

public interface Future<T> {
    T get() throws InterruptedException;
}
