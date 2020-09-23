package com.qihui.sun.lang.generic;

public class TestGeneric {
    public static void main(String[] args) {
        testGenericMethod();

    }

    private static void testGenericMethod() {
        GenericMethod genericMethod = new GenericMethod();
        genericMethod.show(33);
        GenericMethod.showStatic(true);
    }

    private static void testGenericClass() {
        GenericClass<String> genericClass = new GenericClass<String>();
        genericClass.setT("zhang san");
        String t = genericClass.getT();
        System.out.println(t);
    }
}
