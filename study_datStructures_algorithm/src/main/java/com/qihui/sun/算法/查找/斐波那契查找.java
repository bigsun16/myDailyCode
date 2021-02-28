package com.qihui.sun.算法.查找;

import java.util.Arrays;

public class 斐波那契查找 {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 1234};
        System.out.println(Arrays.toString(array));
        int index = search(array, 1);
        System.out.println(index);
    }

    public static int[] getArray() {
        int[] array = new int[maxSize];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array;
    }

    public static int search(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int k = 0;//分割数值的下标
        int mid = 0;
        int[] f = getArray();

        while (high > f[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(array, f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = array[high];
        }
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (value < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (value > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
