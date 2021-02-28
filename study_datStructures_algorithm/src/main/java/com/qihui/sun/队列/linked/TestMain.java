package com.qihui.sun.队列.linked;

public class TestMain {
    public static void main(String[] args) {
        MyLinkedQueue linkedQueue = new MyLinkedQueue();
//        双向链表 linkedQueue = new 双向链表();
        linkedQueue.addLast(1);
        linkedQueue.addFirst(2);
        linkedQueue.addLast(3);
        linkedQueue.addLast(4);
        linkedQueue.printLinked();
        linkedQueue.reverseLinked();
        linkedQueue.printLinked();
        linkedQueue.reverseLinked();
        linkedQueue.printLinked();
    }
}
