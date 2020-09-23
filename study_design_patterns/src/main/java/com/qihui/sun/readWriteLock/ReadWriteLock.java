package com.qihui.sun.readWriteLock;

public class ReadWriteLock {
	private int readingReaderCount = 0;
	private int waitingReaderCount = 0;
	private int writingWriterCount = 0;
	private int waitingWriterCount = 0;
	private boolean preferWrite = true;

	public ReadWriteLock() {
		this(true);
	}

	public ReadWriteLock(boolean preferWrite) {
		this.preferWrite = preferWrite;
	}

	public synchronized void readLock() throws InterruptedException {
		this.waitingReaderCount++;
		try {
			while (this.writingWriterCount > 0 || (this.preferWrite && this.waitingWriterCount > 0)) {
				this.wait();
			}
		} finally {
			this.waitingReaderCount--;
		}
	}

	public synchronized void readUnlock() {
		this.readingReaderCount--;
		this.notifyAll();
	}

	public synchronized void writeLock() throws InterruptedException {
		this.waitingWriterCount++;
		try {
			while (this.readingReaderCount > 0 || this.writingWriterCount > 0) {
				this.wait();
			}
			this.writingWriterCount++;
		} finally {
			this.waitingWriterCount--;
		}
	}

	public synchronized void writeUnlock() {
		this.writingWriterCount--;
		this.notifyAll();
	}
}
