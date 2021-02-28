package com.qihui.sun.算法.排序;

import java.util.Arrays;

public class 快速排序 {
    public static void main(String[] args) {
//        int[] array = {-9, 78, 0, 23, -567, 70};//快速排序
        int[] array = getArray(80000);
        System.out.println(Arrays.toString(array));
        long startTime = System.currentTimeMillis();
        快速排序(array, 0, array.length - 1);
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

    public static void 快速排序(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int midValue = array[(left + right) / 2];
        int temp = 0;
        while (l < r) {
            while (array[l] < midValue) {
                l += 1;
            }
            while (array[r] > midValue) {
                r -= 1;
            }
            if (l >= r) {
                break;
            }

            temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            if (array[l] == midValue) {
                r -= 1;
            }
            if (array[r] == midValue) {
                l += 1;
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }

        if (left < r) {
            快速排序(array, left, r);
        }

        if (right > l) {
            快速排序(array, l, right);
        }
    }
}
