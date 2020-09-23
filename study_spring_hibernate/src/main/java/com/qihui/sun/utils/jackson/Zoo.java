package com.qihui.sun.utils.jackson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zoo implements Serializable {
    private String name;
    private List<Animal> animal = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(List<Animal> animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "name='" + name + '\'' +
                ", animal=" + animal +
                '}';
    }
}
