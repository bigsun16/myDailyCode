package com.qihui.sun.lang.classLoader;

public class TestClassLoader {
    private static final TestClassLoader classLoader = new TestClassLoader();
    public static int x = 1;
    public static int y = 1;

    private TestClassLoader() {
        System.out.println("init TestClassLoader");
        x++;
        y++;
    }

    public static TestClassLoader getClassLoader(){
        return classLoader;
    }

    public static void main(String[] args) {
        System.out.println("main");
        TestClassLoader classLoader = TestClassLoader.getClassLoader();
        System.out.println(x);
        System.out.println(y);
    }
}
