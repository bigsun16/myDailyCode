package com.qihui.sun.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        binaryTree.setRoot(node1);
        binaryTree.prePrint();
        binaryTree.infixPrint();
        binaryTree.postPrint();
    }

    static class BinaryTree{
        private Node root;

        public void setRoot(Node root) {
            this.root = root;
        }
        public void prePrint(){
            if (this.root!=null){
                this.root.prePrint();
                System.out.println();
            } else{
                System.out.println("二叉树为空");
            }
        }
        public void infixPrint(){
            if (this.root!=null){
                this.root.infixPrint();
                System.out.println();
            } else {
                System.out.println("二叉树为空");
            }
        }
        public void postPrint(){
            if (this.root!=null){
                this.root.postPrint();
                System.out.println();
            } else{
                System.out.println("二叉树为空");
            }
        }
    }

    static class Node {
        private final int value;
        private Node left;
        private Node right;

        Node(int value) {
            this.value = value;
        }

        public void prePrint() {
            System.out.print(this);
            if (this.left!=null){
                this.left.prePrint();
            }
            if (this.right!=null){
                this.right.prePrint();
            }
        }

        public void infixPrint() {
            if (this.left!=null){
                this.left.infixPrint();
            }
            System.out.print(this);
            if (this.right!=null){
                this.right.infixPrint();
            }
        }

        public void postPrint() {
            if (this.left!=null){
                this.left.postPrint();
            }
            if (this.right!=null){
                this.right.postPrint();
            }
            System.out.print(this);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
