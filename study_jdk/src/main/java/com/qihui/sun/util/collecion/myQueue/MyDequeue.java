package com.qihui.sun.util.collecion.myQueue;

import java.util.Date;

public class MyDequeue<T> {
    private static Object[] DATA = {};
    private int firstIndex;
    private int lastIndex;

    public MyDequeue() {
        DATA = new Object[8];
    }

    private void expansion() {
        Object[] temp = new Object[size() << 1];
        System.arraycopy(DATA, firstIndex, temp, 0, size() - firstIndex);
        System.arraycopy(DATA, 0, temp, size() - firstIndex, lastIndex);
        firstIndex = 0;
        lastIndex = size();
        DATA = temp;
    }

    public int getFirstIndex(){
        return firstIndex;
    }

    public int getLastIndex(){
        return lastIndex;
    }

    public void addFirst(T t) {
        if (t == null) {
            throw new RuntimeException("value can not be null");
        }
        firstIndex = (firstIndex - 1) & (size() - 1);
        DATA[firstIndex] = t;
        if (firstIndex == lastIndex) {
            expansion();
        }
    }

    public void addLast(T t) {
        if (t == null) {
            throw new RuntimeException("value can not be null");
        }
        lastIndex = (lastIndex + 1) & (size() - 1);
        DATA[lastIndex] = t;
        if (firstIndex == lastIndex) {
            expansion();
        }
    }

    @SuppressWarnings("unchecked")
    public T removeFirst() {
        if (firstIndex==lastIndex){
            return null;
        }
        T t = (T) DATA[firstIndex];
        if (t == null) {
            return null;
        }
        DATA[firstIndex] = null;
        firstIndex = (firstIndex + 1) & (size() - 1);
        return t;
    }

    @SuppressWarnings("unchecked")
    public T removeLast() {
        if (firstIndex==lastIndex){
            return null;
        }
        int tempIndex = (lastIndex - 1) & (size() - 1);
        T t = (T) DATA[tempIndex];
        if (t == null) {
            return null;
        }
        DATA[tempIndex] = null;
        lastIndex = tempIndex;
        return t;
    }

    public int size() {
        return DATA.length;
    }
}
