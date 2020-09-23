package com.qihui.sun.lang.thread;

public class DeadLock {
	static Object lock1 = new Object();
	static Object lock2 = new Object();

	public static void main(String[] args) {
		new Thread(()->{
			while(true) {
				synchronized (lock2) {
					show1();
				}
			}
		}).start();;
		new Thread(()->{
			while(true) {
				synchronized (lock1) {
					show2();
				}
			}
		}).start();;
	}
	
	private static void show1() {
		System.out.println(Thread.currentThread().getName()+" got lock2, want lock1");
		synchronized (lock1) {
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}
	private static void show2() {
		System.out.println(Thread.currentThread().getName()+" got lock1, want lock2");
		synchronized (lock2) {
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}
	
}
