package com.qihui.sun.队列.array;

public class MyArrayCircleQueue {
    private int count;
    private int head;
    private int tail;
    private final int maxSize;
    private final int[] array;

    public MyArrayCircleQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
    }

    public boolean isFull() {
//        return (tail + 1) % maxSize == head;
        return count == maxSize;
    }

    public boolean isEmpty() {
//        return tail == head;
        return count==0;
    }

    public void add(int element) {
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        count++;
        array[tail] = element;
        tail = (tail + 1) % maxSize;
    }

    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        count--;
        int value = array[head];
        head = (head + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        for (int i = head; i < head + size(); i++) {
            System.out.printf("array[%d]=%d\n", i % maxSize, array[i % maxSize]);
        }
    }

    public int size() {
//        return (tail + maxSize - head) % maxSize;
        return count;
    }

    public int headElement() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空。。。");
        }
        return array[head];
    }
}
