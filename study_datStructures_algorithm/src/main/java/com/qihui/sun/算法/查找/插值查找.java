package com.qihui.sun.算法.查找;

import java.util.Arrays;
import java.util.Random;

public class 插值查找 {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = new Random().nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        int index = search(array, 0, 99, 58);
        System.out.println(index);
    }

    private static int search(int[] array, int leftIndex, int rightIndex, int value) {
        System.out.println("查找次数。。。");
        if (leftIndex > rightIndex || value < array[0] || value > array[array.length - 1]) {
            return -1;
        }
        int midIndex = leftIndex + (rightIndex - leftIndex) * (value - array[leftIndex]) / (array[rightIndex] - array[leftIndex]);
        System.out.println(midIndex+"------");
        int midValue = array[midIndex];
        if (value > midValue) {
            return search(array, midIndex + 1, rightIndex, value);
        } else if (value < midValue) {
            return search(array, leftIndex, midIndex - 1, value);
        } else {
            return midIndex;
        }
    }

}
