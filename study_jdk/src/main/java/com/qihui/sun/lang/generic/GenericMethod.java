package com.qihui.sun.lang.generic;

public class GenericMethod {
    public <M> void show(M m){
        System.out.println(m);
    }

    public static <S> void showStatic(S s){
        System.out.println(s);
    }
}
