package com.qihui.sun.jdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestLambda {
    @Test
    public void test1(){
        Runnable runnable = ()->System.out.println("hello lambda");
        runnable.run();
    }
    @Test
    public void test2(){
        testConsumer("hello cpnsumer",s->System.out.println(s.toUpperCase()));
        System.out.println("----------------------");
        List<Double> integers = testSupplier(5, Math::random);
        System.out.println(integers);

        System.out.println("----------------------");
        Integer hello_function = testFunc("hello function", String::length);
        System.out.println(hello_function);
        System.out.println("----------------------");

        List<String> list = Arrays.asList("hello","world","hi");
        List<String> h = testPredicate(list, item -> item.startsWith("h"));
        System.out.println(h);
    }

    public void testConsumer(String name,Consumer<String> consumer){
        consumer.accept(name);
    }

    public List<Double> testSupplier(int count, Supplier<Double> supplier){
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    public Integer testFunc(String string, Function<String,Integer> function){
        return function.apply(string);
    }

    public List<String> testPredicate(List<String> list, Predicate<String> predicate){
        List<String> newList = new ArrayList<>();
        list.forEach((item)->{
            if (predicate.test(item))
                newList.add(item);
        });
        return newList;
    }

@Test
    public void testConstructor(){
        //lambda表达式用的构造函数的参数与函数式接口的参数列表一致
        //        Supplier<Employee> supplier = ()->new Employee();
        Supplier<Employee> supplier = Employee::new;
        System.out.println(supplier.get());
        System.out.println("-----------------------");

//        Function<Integer,Employee> function = (age)->new Employee(age);
        Function<Integer,Employee> function = Employee::new;
        System.out.println(function.apply(35));
    }
}
