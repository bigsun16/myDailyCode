package com.qihui.sun.算法.查找;

import java.util.Arrays;

public class 插值查找 {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }
        System.out.println(Arrays.toString(array));

        int index = search(array, 0, 99, 55);
        System.out.println(index);
    }

    private static int search(int[] array, int left, int right, int value) {
        System.out.println("查找次数。。。");
        if (left > right || value < array[0] || value > array[array.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (value - array[left]) / (array[right] - array[left]);
        int midValue = array[mid];
        if (value > midValue) {
            return search(array, mid + 1, right, value);
        } else if (value < midValue) {
            return search(array, left, mid - 1, value);
        } else {
            return mid;
        }
    }

}
