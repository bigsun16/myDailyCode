package com.qihui.sun.future;

import javax.sound.midi.Soundbank;
import java.util.function.Consumer;

public class TestFutureService {

    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> result = futureService.submit(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                    Thread.sleep(1_000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finished!";
        }, System.out::println);
        System.out.println("do other things...");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            Thread.sleep(1_000);
        }
//        System.out.println(result.get());
    }
}
