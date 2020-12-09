package com.qihui.sun.jdk8;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final Long THRESHOLD = 10000L;
    private final long start;
    private final long end;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end-start;
        long sum = 0L;
        if (length<=THRESHOLD){
            for (long i = start; i <= end; i++) {
                sum+=i;
            }
        } else {
            long mid = (start+end)/2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, mid);
            left.fork();
            ForkJoinCalculate right = new ForkJoinCalculate(mid+1, end);
            right.fork();
            return left.join()+right.join();
        }
        return sum;
    }

    public static void main(String[] args) {
        /*Instant start = Instant.now();
        ForkJoinCalculate task = new ForkJoinCalculate(0L,100000000000L);
        ForkJoinPool pool = new ForkJoinPool();
        Long invoke = pool.invoke(task);
        System.out.println(invoke);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println("------------------------------");//9825
        Instant start2 = Instant.now();
//        long asLong = LongStream.range(0, 1000000000L).parallel().reduce(Long::sum).getAsLong();
        long reduce = LongStream.rangeClosed(0, 100000000000L).parallel().reduce(0, Long::sum);
        System.out.println(reduce);
        Instant end2 = Instant.now();
        System.out.println(Duration.between(start2, end2).toMillis());//6561
        System.out.println("------------------------------");*/
        Instant start3 = Instant.now();
        Long sum3 = 0L;
        for (long i = 0; i < 100000000000L; i++) {
            sum3+=i;
        }
        Instant end3 = Instant.now();
        System.out.println(Duration.between(start3, end3).toMillis());//230247
    }
}
