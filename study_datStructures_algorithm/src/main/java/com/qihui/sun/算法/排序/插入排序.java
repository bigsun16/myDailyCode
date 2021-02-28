package com.qihui.sun.算法.排序;

import java.util.Arrays;

public class 插入排序 {
    public static void main(String[] args) {
//        int[] array = {7, 6, 5, 4, 3, 2, 1};
        int[] array = getArray(80000);
        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        插入排序(array);
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

    public static void 插入排序(int[] array) {
        int count = 0;
        int insertIndex;
        int insertValue;
        for (int i = 0; i < array.length - 1; i++) {
            insertIndex = i;
            insertValue = array[i + 1];
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            if (insertIndex != i) {
                count++;
                array[insertIndex + 1] = insertValue;
            }
        }
        System.out.println("交换次数：" + count);
    }
}
