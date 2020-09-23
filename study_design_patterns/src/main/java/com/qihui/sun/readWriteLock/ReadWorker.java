package com.qihui.sun.readWriteLock;

public class ReadWorker extends Thread {
	private ShareData data;

	public ReadWorker(ShareData data) {
		this.data = data;
	}

	@Override
	public void run() {
		try {
			while (true) {
				char[] readBuf = data.read();
				System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
