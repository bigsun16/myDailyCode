package com.qihui.sun.lang.thread.stopThread;

public class StopThread3 {

	public static void main(String[] args) {
		ThreadService tService = new ThreadService();
		long startTime = System.currentTimeMillis();
		tService.execute(()->{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		tService.shutDown(5000L);
		long endTime = System.currentTimeMillis();
		System.out.println("totalTime: "+(endTime-startTime));
	}
}

class ThreadService{
	
	private boolean shutDownFlag = false;
	private Thread executeThread;
	
	public void execute(Runnable task) {
		executeThread = new Thread() {
			@Override
			public void run() {
				Thread taskThread = new Thread(task);
				taskThread.setDaemon(true);
				taskThread.start();
				try {
					taskThread.join();
					shutDownFlag = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		executeThread.start();
	}
	
	public void shutDown(Long outTime) {
		Long startTime = System.currentTimeMillis();
		while(!shutDownFlag) {
			if((System.currentTimeMillis()-startTime) >= outTime) {
				System.err.println("timeOut ..... task broken");
				executeThread.interrupt();
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		shutDownFlag = false;
	}
	
}
