package com.qihui.sun.队列.linked;

public class 双向链表 {
    private MyNode firstNode;
    private MyNode lastNode;
    private int count;

    public void addLast(int value){
        MyNode newNode = new MyNode(lastNode,value,null);
        if (firstNode == null){
            lastNode = newNode;
            firstNode = lastNode;
        } else {
            lastNode.nextNode = newNode;
            lastNode = newNode;
        }
        count++;
    }

    public void addFirst(int value){
        MyNode newNode = new MyNode(null,value,firstNode);
        if (firstNode == null){
            firstNode = newNode;
            lastNode = firstNode;
        } else {
            firstNode.preNode = newNode;
            firstNode = newNode;
        }
        count++;
    }

    public int removeFirst(){
        int firstValue = firstNode.value;
        firstNode = firstNode.nextNode;
        firstNode.preNode = null;
        count--;
        return firstValue;
    }

    public int removeLast(){
        int lastValue = lastNode.value;
        lastNode = lastNode.preNode;
        lastNode.nextNode = null;
        count--;
        return lastValue;
    }

    public void printLinked() {
        if (firstNode == null) {
            System.out.println("[]");
            return;
        }
        MyNode tempNode = firstNode;
        System.out.print("[");
        while (tempNode.nextNode != null) {
            System.out.print(tempNode.value + ", ");
            tempNode = tempNode.nextNode;
        }
        System.out.println(tempNode.value + "]");
    }
    private static class MyNode {
        private MyNode preNode;
        private final int value;
        private MyNode nextNode;

        MyNode(MyNode preNode, int value, MyNode nextNode) {
            this.preNode = preNode;
            this.value = value;
            this.nextNode = nextNode;
        }
    }
}
