package com.qihui.sun.io.fileHandler1;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class HandlerFileMain {
    private static final String ENCODING = "utf-8";
    //处理文件段大小，默认20000
    private int pageSize = 20000;
    //读取当前CPU个数，决定线程池的大小
    private final static int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    //线程池
    private ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
    //任务队列
    private ArrayBlockingQueue<List<String>> taskQueue = new ArrayBlockingQueue<>(POOL_SIZE);

    private AtomicBoolean isFinish = new AtomicBoolean(false);

    public static void main(String[] args) {
        BigFileReader.Builder builder = new BigFileReader.Builder("d:/reliability.txt",new FileHandler() {

            @Override
            public void handle(String line) {
                //System.out.println(line);
                //increat();
            }
        });
        builder.withThreadSize(10)
                .withCharset("gbk")
                .withBufferSize(1024*1024);
        BigFileReader bigFileReader = builder.build();
        bigFileReader.start();

    }

}
