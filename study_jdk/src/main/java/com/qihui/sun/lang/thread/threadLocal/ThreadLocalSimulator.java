package com.qihui.sun.lang.thread.threadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread thread = Thread.currentThread();
            storage.put(thread, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread thread = Thread.currentThread();
            T value = storage.get(thread);
            if (value == null) {
                return initialValue();
            }
            return value;
        }
    }

    public T initialValue() {
        return null;
    }
}
