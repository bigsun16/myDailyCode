package com.qihui.sun.队列.linked;

public class MyLinkedQueue {
    private MyNode firstNode;
    private MyNode lastNode;
    private int count;

    public MyNode getFirstNode(){
        return this.firstNode;
    }
    public MyNode getLastNode(){
        return this.lastNode;
    }

    public void setFirstNode(MyNode firstNode) {
        this.firstNode = firstNode;
    }

    public void setLastNode(MyNode lastNode) {
        this.lastNode = lastNode;
    }

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

    public void reverseLinked() {
        MyNode newNode = new MyNode(0, null);
        revertLinked(firstNode, newNode);
        lastNode = firstNode;
        lastNode.next = null;
        firstNode = newNode.next;
    }

    private void revertLinked(MyNode oldNode, MyNode newNode) {
        if (oldNode.next == null) {
            newNode.next = oldNode;
        } else {
            MyNode next = oldNode.next;
            revertLinked(next, newNode);
            next.next = oldNode;
            oldNode.next = null;
        }
    }


    public int size() {
        return count;
    }

    public int removeLast() {
        int lastValue = lastNode.value;
        MyNode nodeBeforeLast = firstNode;
        for (int i = 1; i < count - 1; i++) {
            nodeBeforeLast = nodeBeforeLast.next;
        }
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

    public void sort(){
        MyNode current = this.firstNode;
        int temp;
        while (current!=null){
            MyNode next = current.next;
            while (next!=null) {
                if (current.value > next.value){
                    temp = next.value;
                    next.value = current.value;
                    current.value = temp;
                }
                next = next.next;
            }
            current = current.next;
        }
    }

    public static class MyNode {
        private int value;
        private MyNode next;

        MyNode(int value, MyNode next) {
            this.value = value;
            this.next = next;
        }

        public void setNext(MyNode next) {
            this.next = next;
        }
    }
}
