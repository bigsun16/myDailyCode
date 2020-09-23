package com.qihui.sun.lang.thread;
//调用线程的join方法，表示在当前线程结束后再执行join代码之后开启的代码,join可以有参数时间毫秒。表示等待时间
public class TestThreadJoin {
	public static void main(String[] args) throws InterruptedException {
		Thread t0 = new Thread(()->{
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+": "+i);
			}
		},"t0");
		t0.start();
		Thread t1 = new Thread(()->{
			for (int i = 0; i < 16; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+": "+i);
			}
		},"t1");
		t1.start();
		Thread t2 = new Thread(()->{
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+": "+i);
			}
		},"t2");
		t0.join();
		t2.start();
		t1.join();
	}
}
