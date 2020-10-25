package com.qihui.sun.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEmployee {
    List<Employee> list = Arrays.asList(
            new Employee(18,"Zhang San",5555.5D, Employee.Status.BUSY),
            new Employee(15,"li Si",4444.4D,Employee.Status.FREE),
            new Employee(19,"Wang Wu",6666.6D,Employee.Status.VOCATION),
            new Employee(22,"Ma Liu",7777.7D,Employee.Status.FREE),
            new Employee(22,"Ma Liu",7777.7D,Employee.Status.BUSY)
    );

    @Test
    public void test1(){

    }


    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> myPredicate){
        List<Employee> newList = new ArrayList<>();
        for (Employee employee : list) {
            if (myPredicate.test(employee)){
                newList.add(employee);
            }
        }
        return newList;
    }
}
