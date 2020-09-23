package com.qihui.sun.lang.thread.producerConsumer;

public class ProducerConsumer {
	int i = 0;
	private final Object lock = new Object();
	private volatile boolean isConsumedFlag = true;

	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		for (int i = 0; i < 3; i++) {
			new Thread("p" + i) {
				@Override
				public void run() {
					while (true)
						pc.producer();
				}
			}.start();
		}
		for (int i = 0; i < 3; i++) {
			new Thread("c" + i) {
				@Override
				public void run() {
					while (true)
						pc.consumer();
				}
			}.start();
		}
	}

	private void producer() {
		synchronized (lock) {
			while (!isConsumedFlag) {
				try {
					System.out.println(Thread.currentThread().getName() + ": wait");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(500);
				System.out.println(Thread.currentThread().getName() + ": " + (++i));
				isConsumedFlag = false;
				lock.notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void consumer() {
		synchronized (lock) {
			while (isConsumedFlag) {
				try {
					System.out.println(Thread.currentThread().getName() + ": wait");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(500);
				System.out.println(Thread.currentThread().getName() + ": " + i);
				isConsumedFlag = true;
				lock.notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
