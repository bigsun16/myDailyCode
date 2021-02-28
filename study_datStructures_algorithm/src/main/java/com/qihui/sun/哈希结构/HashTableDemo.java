package com.qihui.sun.哈希结构;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:打印雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:推出程序");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入查找id");
                    int num = scanner.nextInt();
                    hashTable.findEmpById(num);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
