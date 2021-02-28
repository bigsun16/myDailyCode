package com.qihui.sun.算法.排序;

import java.util.Arrays;

public class 冒泡排序 {
    public static void main(String[] args) {
//        int[] array = {7, 6, 5, 4, 3, 2, 1};
        int[] array = getArray(80000);
        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        冒泡排序(array);
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

    public static void 冒泡排序(int[] array) {
        int maxValue;
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = false;
            count++;
            for (int y = 0; y < array.length - i - 1; y++) {
                if (array[y] > array[y + 1]) {
                    flag = true;
                    maxValue = array[y];
                    array[y] = array[y + 1];
                    array[y + 1] = maxValue;
                }
            }
            if (!flag) {
                break;
            }
        }
        System.out.println("遍历次数：" + count);
    }
}
