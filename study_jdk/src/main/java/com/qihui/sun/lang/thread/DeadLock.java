package com.qihui.sun.lang.thread;

public class DeadLock {
	static final Object LOCK1 = new Object();
	static final Object LOCK2 = new Object();

	public static void main(String[] args) {
		new Thread(()->{
			while(true) {
				synchronized (LOCK2) {
					show1();
				}
			}
		}).start();
		new Thread(()->{
			while(true) {
				synchronized (LOCK1) {
					show2();
				}
			}
		}).start();
	}
	
	private static void show1() {
		System.out.println(Thread.currentThread().getName()+" got LOCK2, want LOCK1");
		synchronized (LOCK1) {
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}
	private static void show2() {
		System.out.println(Thread.currentThread().getName()+" got LOCK1, want LOCK2");
		synchronized (LOCK2) {
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}
	
}
