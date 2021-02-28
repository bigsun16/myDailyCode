package com.qihui.sun.队列.array;

public class MyArrayQueue {
    private int head;
    private int tail;
    private final int maxSize;
    private final int[] array;

    public MyArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
        this.head = -1;
        this.tail = -1;
    }

    public boolean isFull() {
        return tail == maxSize - 1;
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public void add(int element) {
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        array[++tail] = element;
    }

    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        return array[++head];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("array[%d]=%d\n", i, array[i]);
        }
    }

    public int headElement(){
        if (isEmpty()){
            throw new RuntimeException("队列为空。。。");
        }
        return array[head+1];
    }
}
