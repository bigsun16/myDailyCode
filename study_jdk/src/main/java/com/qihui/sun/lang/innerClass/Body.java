package com.qihui.sun.lang.innerClass;

public class Body {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class Heart{
        public void beat(){
            System.out.println("我是内部类成员方法");
            System.out.println("我是：" +name);
        }
    }

    public void methodBody(){
        System.out.println("我是外部类方法");
        Heart heart = new Heart();
        heart.beat();
    }
}
