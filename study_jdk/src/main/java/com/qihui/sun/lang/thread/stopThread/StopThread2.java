package com.qihui.sun.lang.thread.stopThread;

public class StopThread2 {

	public static void main(String[] args) {
		Thread t1 = new Thread(()->{
			while (true) {
				System.out.println("running ...");
				if (Thread.interrupted())
					break;
			}
		});
		t1.start();
		try {
			Thread.sleep(5000);
			t1.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
