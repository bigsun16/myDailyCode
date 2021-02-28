package com.qihui.sun.哈希结构;

public class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTable(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int empLikedListNo = hashFun(emp.id);
        empLinkedListArray[empLikedListNo].add(emp);
    }

    public int hashFun(int id) {
        return id % size;
    }

    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp!=null){
            System.out.printf("在第%d条链表中找到雇员id=%d\n",(empLinkedListNo+1),id);
        }else{
            System.out.println("在hash表中没有找到该雇员");
        }
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

}
