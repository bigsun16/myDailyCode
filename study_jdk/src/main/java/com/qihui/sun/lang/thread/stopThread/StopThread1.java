package com.qihui.sun.lang.thread.stopThread;

public class StopThread1 {

	public static void main(String[] args) throws InterruptedException {
		Worker task = new Worker();
		task.start();
		Thread.sleep(5000);
		task.shutDown();
	}
}

class Worker extends Thread {

	private volatile boolean runFlag = true;

	public boolean isRunFlag() {
		return runFlag;
	}

	@Override
	public void run() {
		while (runFlag) {
			System.out.println("running...");
		}
	}

	public void shutDown() {
		if (this.isRunFlag()) {
			this.runFlag = false;
		}
	}

}