package com.qihui.sun.util.collecion.myQueue;

public class MyQueue<T> {
    private static Object[] DATA = {};
    private int initCapacity = 5;

    private int removeIndex;
    private int addIndex;

    public MyQueue() {
        DATA = new Object[initCapacity];
    }

    public MyQueue(int initCapacity) {
        this.initCapacity = initCapacity;
        DATA = new Object[this.initCapacity];
    }

    public void add(T t) {
        if (addIndex == size()) {
            expansion();
        }
        DATA[addIndex++] = t;
    }

    private void expansion() {
        int len = addIndex - removeIndex < size() ? size() : size() + initCapacity;
        Object[] tempArray = new Object[len];
        System.arraycopy(DATA, removeIndex, tempArray, 0, addIndex - removeIndex);
        DATA = tempArray;
        addIndex = addIndex - removeIndex;
        removeIndex = 0;
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        if (removeIndex >= size()) {
            return null;
        }
        return (T) DATA[removeIndex++];
    }

    public int size() {
        return DATA.length;
    }

    public int elementsCount() {
        return addIndex - removeIndex;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= removeIndex && index < addIndex) {
            return (T) DATA[index];
        } else {
            return null;
        }
    }
}
