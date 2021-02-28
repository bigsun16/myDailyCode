package com.qihui.sun.算法.查找;

public class 线性查找 {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = searchIndex(arr, 11);
        System.out.println(index);
    }

    public static int searchIndex(int[] array, int value){
        for (int i = 0; i < array.length; i++) {
            if (array[i]==value){
                return i;
            }
        }
        return -1;
    }
}
