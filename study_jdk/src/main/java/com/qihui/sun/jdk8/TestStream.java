package com.qihui.sun.jdk8;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    List<Employee> list = Arrays.asList(
            new Employee(18,"Zhang San",5555.5D, Employee.Status.BUSY),
            new Employee(15,"li Si",4444.4D,Employee.Status.FREE),
            new Employee(19,"Wang Wu",6666.6D,Employee.Status.VOCATION),
            new Employee(22,"Ma Liu",7777.7D,Employee.Status.FREE),
            new Employee(22,"Ma Liu",7777.7D,Employee.Status.BUSY)
    );

    @Test
    public void test1(){
        list.stream()
                .filter((e)-> {
//                    System.out.println("中间操作");//只有当执行到forEach()类似的结束遍历的方法时才执行
                    return e.getAge()>14;
                })
//                .limit(2)//取前两个满足条件的元素，终止遍历后续元素
//                .skip(2)//取最后两个满足条件的元素
                .distinct()//去重，依赖hashCode()和equals()
                .forEach(System.out::println);

    }

    @Test
    public void test2(){
//        Stream.of("aa","bb","cc")
//                .map(String::toUpperCase)
//                .forEach(System.out::println);
//        System.out.println("==================================");

//        list.stream().map(Employee::getName)
//                .forEach(System.out::println);
//        System.out.println("==================================");

//        Stream<Stream<Character>> streamStream = Stream.of("aa", "bb", "cc").map(TestStream::filterString);
//        streamStream.forEach((sm)->sm.forEach(System.out::println));
//        System.out.println("==================================");

        Stream<Character> characterStream = Stream.of("aa", "bb", "cc").flatMap(TestStream::filterString);
        characterStream.forEach(System.out::println);

    }

    private static Stream<Character> filterString(String string){
        List<Character> array = new ArrayList<>();
        for (char c : string.toCharArray()) {
            array.add(c);
        }
        return array.stream();
    }

    //自然排序：Comparable
    //定制排序：Comparator
    @Test
    public void test3(){
        Stream.of("bb","aa","yy","vv").sorted().forEach(System.out::println);

        list.stream().sorted((e1,e2)->{
            if (e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            }else{
                return e1.getAge().compareTo(e2.getAge());
            }

        }).forEach(System.out::println);
    }

    @Test
    public void test4(){
        list.stream()
                .collect(Collectors.groupingBy(Employee::getStatus))
                .values().stream()
                .map(employees -> employees.stream()
                        .max(Comparator.comparing(Employee::getSalary))
                        .get()
                ).collect(Collectors.toList()).forEach(System.out::println);
    }
}
