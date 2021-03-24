package com.qihui.sun.算法.查找;

import java.util.ArrayList;
import java.util.LinkedList;

//数组必须是有序的
public class 二分查找 {
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 1000, 1000,1000, 1000, 1000 ,1000, 1000, 1000,1000, 1000, 1000, 1234};
//        int index = searchIndex(array, 0, array.length - 1, 1000);
        LinkedList<Integer> index = searchIndexArray(array, 0, array.length - 1, 1000);
        System.out.println(index);
    }

    public static int searchIndex(int[] array, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = array[mid];
        if (value > midValue) {
            return searchIndex(array, mid + 1, right, value);
        } else if (value < midValue) {
            return searchIndex(array, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    public static LinkedList<Integer> searchIndexArray(int[] array, int left, int right, int value) {

        if (left > right) {
            return new LinkedList<>();
        }
        int mid = (left + right) / 2;
        int midValue = array[mid];
        if (value > midValue) {
            return searchIndexArray(array, mid + 1, right, value);
        } else if (value < midValue) {
            return searchIndexArray(array, left, mid - 1, value);
        } else {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(mid);
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || array[temp] != value) {
                    break;
                }
                list.addFirst(temp);
                temp -= 1;
            }
            temp = mid + 1;
            while (true) {
                if (temp > array.length - 1 || array[temp] != value) {
                    break;
                }
                list.addLast(temp);
                temp += 1;
            }
            return list;
        }
    }
}
