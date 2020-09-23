package com.qihui.sun.lang.thread;

//守护线程是指，如果父线程提前子线程死掉了，那么子线程也会跟着结束。如果应用中有活着的线程，应用不会真正结束结束
public class TestDaemonThread {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(()-> {
			Thread innerThread = new Thread(()-> {
				try {
					Thread.sleep(10_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" die...");
			
			},"innerThread");
			innerThread.setDaemon(true);
			innerThread.start();
			
			try {
				Thread.sleep(5_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"done...");
		},"outerThread");
		thread.start();
		
		/*for(int i=1;i<=30;i++) {
			Thread.sleep(1000);
			System.out.println(i);
		}*/
	}
}
