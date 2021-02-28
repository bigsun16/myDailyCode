package com.qihui.sun.队列.array;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
//        MyArrayQueue myArrayQueue = new MyArrayQueue(3);
        MyArrayCircleQueue circleQueue = new MyArrayCircleQueue(3);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("-------------------------");
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):推出程序");
            System.out.println("a(add):添加数据");
            System.out.println("r(remove):移除数据");
            System.out.println("h(head):查看队列头数据");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    circleQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    circleQueue.add(scanner.nextInt());
                    break;
                case 'r':
                    try {
                        System.out.println("移出的数据：" + circleQueue.remove());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("头部数据是:" + circleQueue.headElement());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
