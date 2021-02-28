package com.qihui.sun.算法.排序;

import java.util.Arrays;

public class 基数排序 {
    public static void main(String[] args) {
//        int[] array = {53, 3, 542, 748, 14, 214};
        int[] array = getArray(80000);
        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        sort(array);
        System.out.println("总耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println(Arrays.toString(array));

    }

    public static void sort(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int maxLength = (max + "").length();
        int[][] bucket = new int[10][array.length];
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < array.length; j++) {
                int digitOfElement = array[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        array[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
    }

    public static int[] getArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

}
