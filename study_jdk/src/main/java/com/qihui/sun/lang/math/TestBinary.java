package com.qihui.sun.lang.math;

import java.util.Map;

/**
 *  与 &   1&1-->1,其它都为0
 *  或 |   0|0-->0,其他都为1
 *  取反~  1-->0，0-->1
 *  异或^  两位相同则为0，不同则为1
 *  左移<< 低位补0
 *  右移>> 非符号数高位补0，符号数高位补符号位1
 */
public class TestBinary {
    // num/2
    private static int show1(int num){
        return num >> 1;
    }

    // num*2
    private static int show2(int num){
        return num << 1;
    }

    // 交换两个数字 1
    private static void show3(Map<String,Integer> map){
        Integer a = map.get("a");
        Integer b = map.get("b");
        a = a+b;
        b = a-b;
        a = a-b;
        map.put("a",a);
        map.put("b",b);
    }

    // 交换两个数字 2
    private static void show4(Map<String,Integer> map){
        Integer a = map.get("a");
        Integer b = map.get("b");
        a^=b;//a = a^b;
        b^=a;//b = a^b;
        a^=b;//a = a^b;
        map.put("a",a);
        map.put("b",b);
    }

    //判断一个数是否为偶数
    private static boolean show5(int num){
        return 0 == (num & 1);
    }

    //正负号转换
    private static int show6(int num){
        return ~num + 1;
    }

    //求绝对值
    private static int show7(int num){
        int flag = num >> 31;
        return flag==0 ? num : show6(num);
//		return ((num^flag) - flag);
    }

    //判断一个数字的第n位是不是1,位数从0开始
    private static boolean show8(int num, int n){
        return 0 != (num & (1<<n));
    }

    public static void main(String[] args) {
    }
}
