package com.qihui.sun.readWriteLock;

public class ReadWriteLockClient {
	public static void main(String[] args) {
		final ShareData data = new ShareData(10);
		new ReadWorker(data).start();
		new ReadWorker(data).start();
		new ReadWorker(data).start();
		new ReadWorker(data).start();
		new WriteWorker(data, "qwertyuiop").start();
		new WriteWorker(data, "QWERTYUIOP").start();
	}	

}
