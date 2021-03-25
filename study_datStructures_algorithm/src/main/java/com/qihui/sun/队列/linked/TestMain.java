package com.qihui.sun.队列.linked;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestMain {
    /*public static void main(String[] args) {
        MyLinkedQueue linkedQueue1 = new MyLinkedQueue();
//        双向链表 linkedQueue = new 双向链表();
        linkedQueue1.addLast(1);
        linkedQueue1.addLast(2);
        linkedQueue1.addLast(4);
        MyLinkedQueue linkedQueue2 = new MyLinkedQueue();
        linkedQueue2.addLast(1);
        linkedQueue2.addLast(3);
        linkedQueue2.addLast(4);
        linkedQueue1.printLinked();
        linkedQueue2.printLinked();


    }*/

    @Test
    public void test(){
        MyLinkedQueue list1 = new MyLinkedQueue();
        list1.addLast(1);
        list1.addLast(2);
        list1.addLast(4);
        MyLinkedQueue list2 = new MyLinkedQueue();
        list2.addLast(1);
        list2.addLast(3);
        list2.addLast(4);
        list1.getLastNode().setNext(list2.getFirstNode());
        list1.setLastNode(list2.getLastNode());
        list1.printLinked();
        list1.sort();
        list1.printLinked();
        /*for (int i = 0; i < list1.size(); i++) {
            int minIndex = 0;
            Integer minValue = list1.get(i);
            for (int j = 0; j < list2.size(); j++) {
                if (list2.get(j)<minValue){
                    minValue = list2.get(j);
                    minIndex = j;
                }
            }
            newList.addLast(minValue);
        }*/

    }

    @Test
    public void test2(){
        System.out.println(Math.round(1.3));
        System.out.println(Math.round(1.5));
        System.out.println(Math.round(1.7));
        System.out.println("---------------");
        System.out.println(Math.ceil(1.3));
        System.out.println(Math.ceil(1.5));
        System.out.println(Math.ceil(1.7));
        System.out.println("---------------");
        System.out.println(Math.floor(1.3));
        System.out.println(Math.floor(1.5));
        System.out.println(Math.floor(1.7));
    }

    public static void main(String[] args) {
        System.out.println(show());
    }

    private static int show() {
        int a = 0;
        try{
            int b = a/0;
            a = 3;
        }catch (Exception e){
            a = 8;
            return a;
        }finally {
            a = 7;
        }
        return a;
    }

}