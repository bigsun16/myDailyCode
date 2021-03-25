package com.qihui.sun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

/**
 * @author qihuis
 */
public class Interview {

    public static void main(String[] args) throws IOException {
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//test9();
        test10();
    }

    private static void test10() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String str = String.valueOf(num);
        System.out.println(new StringBuilder(str).reverse().toString());
    }

    private static void test9() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        TreeSet<Character> set = new TreeSet<>();
        for (char c : line.toCharArray()) {
            if ((int)c>0 && (int)c<127){
                set.add(c);
            }
        }
        System.out.println(set.size());
    }

    private static void test8() {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.next();
        char[] chars = num.toCharArray();
        LinkedList<Character> list = new LinkedList<>();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if (!list.contains(chars[length-1-i])){
                list.addLast(chars[length-1-i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        list.forEach(sb::append);

        System.out.println(Integer.parseInt(sb.toString()));
    }

    private static void test7() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            System.out.println(new BigInteger(next,10).toString(16));
        }
    }

    private static void test6() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < num; i++) {
            set.add(new Random().nextInt(num));
        }
        System.out.println(set);
    }


    private static void test5() {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();
        String line = scanner.nextLine();
        int flag = scanner.nextInt();
        String[] array = line.split(" ");
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            nums[i] = Integer.parseInt(array[i]);
        }
        if (flag==0){
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
        }else if (flag==1){
            Arrays.sort(nums);
            int[] newArray = new int[count];
            for (int i = 0; i < nums.length; i++) {
                newArray[i] = nums[nums.length-i-1];
            }
            System.out.println(Arrays.toString(newArray));
        }
    }
    public static void test4(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            Map<Character,Integer> map = new HashMap<>();
            for (char c : next.toCharArray()) {
                if (map.containsKey(c)){
                    map.put(c,map.get(c)+1);
                } else {
                    map.put(c,1);
                }
            }
            Map<Integer,Character> map2 = new TreeMap<>();
            map.forEach((c,count)->{
                map2.put(map.get(c)*128+128-c,c);
            });
            StringBuilder sb = new StringBuilder();
            map2.forEach((order,c)->{
                sb.append(c);
            });
            System.out.println(sb.reverse().toString());
        }
    }
    public static void test3(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int num = scanner.nextInt();
            for (int i = 0; i < num; i++) {
                String next = scanner.next();
                while (next.length()%8!=0){
                    next = next.concat("0");
                }
                for (int i1 = 0; i1 < next.length()/8; i1++) {
                    System.out.println(next.substring(i1 * 8, i1 * 8 + 8));
                }
            }
        }
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
