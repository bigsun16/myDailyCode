package com.qihui.sun.utils.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

@JsonDeserialize(as = Animal.class)
public abstract class Animal implements Serializable {
    private String id;
    public enum AnimalType {
        Cat,Pig,Dog
    }
    protected AnimalType animalType;

    public Animal(){}

    public Animal(AnimalType animalType) {
        this();
        this.animalType = animalType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", animalType=" + animalType +
                '}';
    }
}
