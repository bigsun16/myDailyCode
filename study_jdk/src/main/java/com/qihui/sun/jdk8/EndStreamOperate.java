package com.qihui.sun.jdk8;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EndStreamOperate {
    List<Employee> employees = Arrays.asList(
            new Employee(15, "Zhang San", 5555.5D, Employee.Status.BUSY),
            new Employee(33, "Li Si", 4444.4D, Employee.Status.FREE),
            new Employee(18, "Zhang San", 56655.5D, Employee.Status.BUSY),
            new Employee(23, "Li Si", 4454.4D, Employee.Status.FREE),
            new Employee(12, "Zhang San", 5155.5D, Employee.Status.BUSY),
            new Employee(36, "Li Si", 4344.4D, Employee.Status.FREE),
            new Employee(11, "Zhang San", 55755.5D, Employee.Status.BUSY),
            new Employee(15, "Li Si", 4466.4D, Employee.Status.FREE)
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
        Map<Integer, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(collect);

        Map<Boolean, List<Employee>> collect1 = employees.stream().collect(Collectors.partitioningBy((e) -> e.getSalary() > 4000));

        String collect2 = employees.stream().map(Employee::getName).collect(Collectors.joining(",", "===", "==="));
        System.out.println(collect2);

        Optional<Integer> reduce = employees.stream().map((x) -> 1).reduce(Integer::sum);
    }

    @Test
    public void test5(){
        Map<String, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getName));

        collect.forEach((key,value)->{
            LinkedHashMap<Integer, Double> collect1 = value.stream().sorted((o1, o2) -> o2.getAge().compareTo(o1.getAge())).collect(Collectors.toMap(Employee::getAge, Employee::getSalary, (oldVal, newVal) -> oldVal,
                    LinkedHashMap::new));
            /*LinkedHashMap<Integer, Double> collect2 = collect1.entrySet().stream().sorted((o1, o2) -> {
                return o1.getKey().compareTo(o2.getKey());
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal,
                    LinkedHashMap::new));*/
            System.out.println(collect1);
            System.out.println(collect1.keySet());
        });
    }

    @Test
    public void test6(){
    	Map<String,Object> map1 = new HashMap<String, Object>();
    	map1.put("aa", 11);
    	map1.put("bb", 22);
    	map1.put("cc", 33);
    	Map<String,Object> map2 = new HashMap<String, Object>();
    	map2.put("dd", 44);
    	map1.entrySet().iterator();
    	System.out.println(map1);
    	LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    }

    @Test
    public void test7(){
        List<String> list = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "0","1","2","3","4","5","6","7","8","9",
                "!","@","#","&",".");
        Collections.shuffle(list);
        List<String> strings = list.subList(0, 13);
        strings.forEach(System.out::print);
        //MyWcGquVpB@#4
    }

}
