package com.qihui.sun.utils.jackson;

public class CatAnimal extends Animal {
    public CatAnimal() {
        super(AnimalType.Cat);
    }

    private Integer num;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CatAnimal{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
