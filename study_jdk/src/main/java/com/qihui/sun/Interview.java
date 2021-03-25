package com.qihui.sun;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qihuis
 */
public class Interview {

    public static void main(String[] args) throws IOException {
//        test2();
        System.out.println(14/10);
        System.out.println(14/10.0);
    }
    public static void test1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.nextLine();
            StringBuilder sb = new StringBuilder(next);
            System.out.println(sb.reverse());
        }
    }

    public static void test2() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        String[] nums = str.split(" ");
        int count1 = 0;
        int count2 = 0;
        int sum = 0;
        for (String num : nums) {
            if ("".equals(num) || "0".equals(num)){
                continue;
            }
            int i = Integer.parseInt(num);
            int compare = Integer.compare(i, 0);
            if (compare<0){
                count1++;
            }else{
                count2++;
                sum+=i;
            }
        }
        System.out.println("负数个数："+count1);
        double avg = Math.round(sum*10.0/count2)/10.0;
        System.out.println("非负数平均值："+avg);
    }
}
