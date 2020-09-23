package com.qihui.sun.util.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class TestAtomicReference {

    private static final int threadCount = 10;
    private static final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
    private static final AtomicReference<User> atomicReference = new AtomicReference<>();

    private static class ReferenceUserUpdateTask implements Runnable {
        User user;

        public ReferenceUserUpdateTask(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                User oldUser = atomicReference.get();
                atomicReference.compareAndSet(oldUser, user);
                Thread.yield();
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < threadCount; i++) {
            new Thread(new ReferenceUserUpdateTask(new User("name" + i, 20 + i))).start();
        }
        countDownLatch.await();
        System.out.println(atomicReference.get());
    }

    private static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
