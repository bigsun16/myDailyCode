package com.qihui.sun.lang;

import java.util.Arrays;

public class TestSystem {
    public static void main(String[] args) {
        String getenv = System.getenv("JAVA_HOME");
//        System.getProperties().list(System.out);
        String properties = System.getProperty("user.dir");
        System.out.println(properties);
        System.out.println(getenv);
    }

    private static void testArrayCopy() {
        String[] list = {"a","b","c","d"};
        String[] strings = Arrays.copyOf(list, 6);
        System.arraycopy(list,0,strings,3,2);
        System.out.println(Arrays.asList(strings));
    }
}
