package com.qihui.sun.readWriteLock;

import java.util.Random;

public class WriteWorker extends Thread {
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private final ShareData data;
	private final String filter;
	private int index = 0;

	public WriteWorker(ShareData data, String filter) {
		this.filter = filter;
		this.data = data;
	}

	@Override
	public void run() {
		try {
			while (true) {
				data.write(nextChar());
				Thread.sleep(RANDOM.nextInt(1000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private char nextChar() {
		char c = filter.charAt(index++);
		if (index > filter.length()) {
			index = 0;
		}
		return c;
	}
}
