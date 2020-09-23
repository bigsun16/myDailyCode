package com.qihui.sun.util.collecion.myQueue;

public class TestMyQueue {
    public static void main(String[] args) {
        testMyOneWayQueue();
    }

    private static void testMyOneWayQueue() {
        MyQueue<Integer> list = new MyQueue<Integer>();
        System.out.println("length:"+list.size());
        System.out.println("elementsCount:"+list.elementsCount());
        System.out.println("get:"+list.get(0));
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println("get:"+list.get(0));
        System.out.println("length:"+list.size());
        System.out.println("elementsCount:"+list.elementsCount());
        for (int i = 0; i<= 2; i++){
            System.out.println(list.remove());
        }
        for (int i = 0; i< list.size(); i++){
            System.out.println("get:"+list.get(i));
        }
        System.out.println("length:"+list.size());
        System.out.println("elementsCount:"+list.elementsCount());

    }
}
