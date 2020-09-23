package com.qihui.sun.lang.innerClass;

public class OuterClass {
    int num = 10;

    public class InnerClass{
        int num = 20;

        public void innerMethod(){
            int num = 30;
            System.out.println(num);
            System.out.println(this.num);
            System.out.println(OuterClass.this.num);
        }
    }

    public void outerMethod(){
//      这个final必须写
        final int sum = 0;
        class MethodInnerClass{
//          这个final可以省略，但依然是final的
            final int num = 40;
            public void method(){
//                局部内部类访问局部变量，必须是final的，跟生命周期有关，方法变量会早于对象消失，
//                所以为保证方法结束后，对象还能访问变量，就得将变量修饰为final的不可变的
//                如果变来变去，那么内部类就不知道用哪个值了
                System.out.println(sum);
                System.out.println(num);
                System.out.println(this.num);
                System.out.println(OuterClass.this.num);
            }
        }
//        局部内部类只能在方法内部创建对象，出了方法就无法调用
        MethodInnerClass innerClass = new MethodInnerClass();
        innerClass.method();
    }

}
