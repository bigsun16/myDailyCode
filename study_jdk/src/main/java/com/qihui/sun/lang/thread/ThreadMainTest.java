package com.qihui.sun.lang.thread;

import java.util.LinkedList;

public class ThreadMainTest {
    private static final LinkedList<String> TASK_QUEUE_NOT_RUNNING = new LinkedList<>();
    public static void main(String[] args) {
        TASK_QUEUE_NOT_RUNNING.add("1");
        TASK_QUEUE_NOT_RUNNING.add("2");
        TASK_QUEUE_NOT_RUNNING.add("3");
        TASK_QUEUE_NOT_RUNNING.add("4");
        TASK_QUEUE_NOT_RUNNING.add("5");
        String s1 = TASK_QUEUE_NOT_RUNNING.removeFirst();
        System.out.println(s1);
        String s2 = TASK_QUEUE_NOT_RUNNING.removeFirst();
        System.out.println(s2);
        String s3 = TASK_QUEUE_NOT_RUNNING.removeFirst();
        System.out.println(s3);
        String s4 = TASK_QUEUE_NOT_RUNNING.removeFirst();
        System.out.println(s4);
        String s5 = TASK_QUEUE_NOT_RUNNING.removeFirst();
        System.out.println(s5);
        String s6 = TASK_QUEUE_NOT_RUNNING.removeFirst();
        System.out.println(s6);

    }
}
