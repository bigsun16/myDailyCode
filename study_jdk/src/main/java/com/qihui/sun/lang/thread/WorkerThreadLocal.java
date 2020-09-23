package com.qihui.sun.lang.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//设置同时最大执行线程数
public class WorkerThreadLocal {

	public static void main(String[] args) {

		List<Thread> workers = new ArrayList<>();
		Long startTime = System.currentTimeMillis();
		for (int i=1;i<=10;i++) {
			Worker worker = new Worker("w"+i);
			worker.start();
			workers.add(worker);
		}
		for (Thread worker : workers) {
			try {
				worker.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("all works are finished..waste total time is: "+(endTime-startTime));
	}
}

class Worker extends Thread{
	private String name;
	private final int maxExecuteThread = 5;
	private static final LinkedList<Object> locks = new LinkedList<>();
	
	public Worker(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		synchronized (locks) {
			while(locks.size()>=maxExecuteThread) {
				try {
					System.out.println(name+" is wait");
					locks.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			locks.addFirst(name);
			System.out.println(locks);
		}
		
		try {
			System.out.println(name+" is running...");
			Thread.sleep(5_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (locks) {
			System.out.println(name+" has finished");
			locks.removeFirst();
			locks.notifyAll();
		}
		
	}
}