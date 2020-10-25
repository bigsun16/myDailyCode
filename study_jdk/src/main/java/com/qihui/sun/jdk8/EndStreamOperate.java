package com.qihui.sun.jdk8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EndStreamOperate {
    List<Employee> employees = Arrays.asList(
            new Employee(18, "Zhang San", 5555.5D, Employee.Status.BUSY),
            new Employee(15, "li Si", 4444.4D, Employee.Status.FREE),
            new Employee(19, "Wang Wu", 6666.6D, Employee.Status.VOCATION),
            new Employee(22, "Ma Liu", 7777.7D, Employee.Status.FREE),
            new Employee(22, "Ma Liu", 7777.7D, Employee.Status.BUSY)
    );

    @Test
    public void test() {
        boolean b = employees.stream()
//                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
//                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
//                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        Optional<Employee> first = employees.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(first.get());

        Optional<Employee> max = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(first.get());

        Optional<Employee> any = employees.parallelStream().filter((e) -> e.getStatus().equals(Employee.Status.BUSY))
                .findAny();//parallelStream()并行流，谁先获取到算谁的
        System.out.println(any.get());

        Optional<Double> max1 = employees.stream().map(Employee::getSalary).max(Double::compareTo);
        System.out.println(max1.get());

    }

    @Test
    public void test2() {
        //归约 reduce()可以将流中的元素反复结合起来，得到一个结果

        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(0, (a, b) -> a + b);

        Optional<Double> reduce1 = employees.stream().map(Employee::getSalary).reduce(Double::sum);

    }

    @Test
    public void test3() {
        List<String> collect = employees.stream().map(Employee::getName).collect(Collectors.toList());
        LinkedHashSet<String> collect1 = employees.stream().map(Employee::getName).collect(Collectors.toCollection(LinkedHashSet::new));

        Double collect2 = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        Double collect5 = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        Optional<Employee> collect4 = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
        DoubleSummaryStatistics collect3 = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        double average = collect3.getAverage();
        long count = collect3.getCount();
        double max = collect3.getMax();
        double min = collect3.getMin();
        double sum = collect3.getSum();
    }

    @Test
    public void test4() {
        Map<Employee.Status, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);

        Map<Boolean, List<Employee>> collect1 = employees.stream().collect(Collectors.partitioningBy((e) -> e.getSalary() > 4000));

        String collect2 = employees.stream().map(Employee::getName).collect(Collectors.joining(",", "===", "==="));
        System.out.println(collect2);

        Optional<Integer> reduce = employees.stream().map((x) -> 1).reduce(Integer::sum);
    }

}
