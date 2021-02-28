package com.qihui.sun.算法.排序;

import java.util.Arrays;

public class 希尔排序 {
    public static void main(String[] args) {
//        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};//希尔排序
        int[] array = getArray(80000);
        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        希尔排序1(array);
        希尔排序2(array);
        System.out.println("总耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println(Arrays.toString(array));
    }

    public static int[] getArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

    //交换法
    public static void 希尔排序1(int[] array) {
        int temp = 0;
        for (int count = array.length / 2; count > 0; count /= 2) {
            for (int i = count; i < array.length; i++) {
                for (int j = i - count; j >= 0; j -= count) {
                    if (array[j] > array[j + count]) {
                        temp = array[j];
                        array[j] = array[j + count];
                        array[j + count] = temp;
                    }
                }
            }
        }
    }

    //移动发
    public static void 希尔排序2(int[] array) {
        for (int count = array.length / 2; count > 0; count /= 2) {
            for (int i = count; i < array.length; i++) {
                int j = i;
                int temp = array[j];
                if (array[j] < array[j - count]) {
                    while (j - count >= 0 && temp < array[j - count]) {
                        array[j] = array[j - count];
                        j -= count;
                    }
                    array[j] = temp;
                }
            }
        }
    }
}
