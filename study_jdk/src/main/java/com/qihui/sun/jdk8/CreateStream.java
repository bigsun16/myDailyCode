package com.qihui.sun.jdk8;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreateStream {

    @Test
    public void test1(){
        //1.可以通过Collection系列集合的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

//        2.可以通过Arrays的静态方法Stream获取流
        Object[] array = new Object[2];
        Stream<Object> stream2 = Arrays.stream(array);

//        3.通过Stream的静态方法of()
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);

//        4.创建无线流
//        4.1迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);

//        4.2生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(5).forEach(System.out::println);
    }


}
