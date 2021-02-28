package com.qihui.sun.算法.排序;

import java.util.Arrays;

public class 选择排序 {
    public static void main(String[] args) {
//        int[] array = {7, 6, 5, 4, 3, 2, 1};
        int[] array = getArray(80000);
        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        选择排序(array);
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

    public static void 选择排序(int[] array) {

        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            count++;
            int minIndex = i;
            int minValue = array[i];
            for (int y = i + 1; y < array.length; y++) {
                if (minValue > array[y]) {
                    minValue = array[y];
                    minIndex = y;
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = minValue;
            }
        }
        System.out.println("遍历次数：" + count);
    }
}
