package com.qihui.sun.算法.排序;

import java.util.Arrays;

public class 归并排序 {
    public static void main(String[] args) {
        int[] array = getArray(80000);
//        int[] array = {8, 4, 5, 7, 1, 3, 6, 2};//merge
        int[] temp = new int[array.length];//merge
        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        mergeSort(array, 0, array.length - 1, temp);
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

    public static void 归并排序(int[] array, int leftIndex, int midIndex, int rightIndex, int[] temp) {
        int i = leftIndex;
        int j = midIndex + 1;
        int t = 0;//temp[]当前索引

        while (i <= midIndex && j <= rightIndex) {
            if (array[i] <= array[j]) {
                temp[t] = array[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = array[j];
                t += 1;
                j += 1;
            }
        }
        while (i <= midIndex) {
            temp[t] = array[i];
            t += 1;
            i += 1;
        }
        while (j <= rightIndex) {
            temp[t] = array[j];
            t += 1;
            j += 1;
        }
        t = 0;
        int tempLeft = leftIndex;
        while (tempLeft <= rightIndex) {
            array[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

    private static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(array, left, mid, temp);
            //向右递归分解
            mergeSort(array, mid + 1, right, temp);
            归并排序(array,left,mid,right,temp);
        }
    }
}
