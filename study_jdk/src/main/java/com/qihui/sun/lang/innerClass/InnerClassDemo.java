package com.qihui.sun.lang.innerClass;

public class InnerClassDemo {
    public static void main(String[] args) {
        OuterClass.InnerClass innerClass = new OuterClass().new InnerClass();
        innerClass.innerMethod();
        System.out.println("==========================");
        OuterClass outerClass = new OuterClass();
        outerClass.outerMethod();
    }

    public static void testBody() {
        //        创建外部类对象调用内部类
        Body body = new Body();
        body.methodBody();
        System.out.println("==========================");
//        直接创建内部类对象，公式：外部类.内部类 引用名 = new 外部类().new 内部类();
        Body.Heart heart = new Body().new Heart();
        heart.beat();
    }
}
