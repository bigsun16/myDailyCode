package com.qihui.sun.lang;

public class TestString {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("hello");
        changeStringBuffer(sb);
        System.out.println(sb);
    }

    private static void changeStringBuffer(StringBuffer sb) {
        sb = new StringBuffer();
        sb.append(" world");
    }

    private static void testString1() {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println(a.equals(c));
        System.out.println(a.equals(e));
    }
}
