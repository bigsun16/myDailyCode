package com.qihui.sun.队列.linked;

public class MyLinkedQueue {
    private MyNode firstNode;
    private MyNode lastNode;
    private int count;

    public void addLast(int element) {
        MyNode newNode = new MyNode(element, null);
        if (firstNode == null) {
            lastNode = newNode;
            firstNode = lastNode;
        } else {
            lastNode.next = newNode;
            lastNode = newNode;
        }
        count++;
    }

    public void addFirst(int element) {
        MyNode newNode = new MyNode(element, null);
        if (firstNode == null) {
            firstNode = newNode;
            lastNode = firstNode;
        } else {
            newNode.next = firstNode;
            firstNode = newNode;
        }
        count++;
    }

    public int removeFirst() {
        int firstValue = firstNode.value;
        firstNode = firstNode.next;
        count--;
        return firstValue;
    }

    public void reverseLinked(){
        MyNode newNode = new MyNode(0,null);
        revertLinked(firstNode, newNode);
        lastNode = firstNode;
        lastNode.next = null;
        firstNode = newNode.next;
    }

    private void revertLinked(MyNode oldNode, MyNode newNode) {
        if (oldNode.next==null){
            newNode.next = oldNode;
        }else{
            MyNode next = oldNode.next;
            revertLinked(next,newNode);
            next.next = oldNode;
            oldNode.next = null;
        }
    }


    public int size(){
        return count;
    }

    public int removeLast() {
        int lastValue = lastNode.value;
        MyNode nodeBeforeLast = firstNode;
        for (int i = 1; i < count-1; i++)
            nodeBeforeLast = nodeBeforeLast.next;
        nodeBeforeLast.next = null;
        lastNode = nodeBeforeLast;
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
        while (tempNode.next != null) {
            System.out.print(tempNode.value + ", ");
            tempNode = tempNode.next;
        }
        System.out.println(tempNode.value + "]");
    }

    private static class MyNode {
        private final int value;
        private MyNode next;

        MyNode(int value, MyNode next) {
            this.value = value;
            this.next = next;
        }
    }
}
