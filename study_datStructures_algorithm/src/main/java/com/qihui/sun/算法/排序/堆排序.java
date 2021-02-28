package com.qihui.sun.算法.排序;

import java.util.Arrays;

public class 堆排序 {
    public static void main(String[] args) {
//        int[] array = {4, 6, 8, 5, 9,-1,0,66,33};
        int[] array = getArray(8000000);
//        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        sort(array);
        System.out.println("总耗时：" + (System.currentTimeMillis() - startTime));
//        System.out.println(Arrays.toString(array));
    }

    public static int[] getArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

    private static void sort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        int temp = 0;
        for (int j = array.length - 1; j > 0; j--) {
            temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            adjustHeap(array, 0, j);
        }
    }

    public static void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }
}
