package com.qihui.sun.lang.thread;

import java.lang.Thread.UncaughtExceptionHandler;

public class CatchThreadException {

	public static void main(String[] args) {
		Thread thread = new Thread(()->{
			int i = 4 / 0;
		});
		thread.setUncaughtExceptionHandler((t,e)->{
				System.out.println("thread:"+t);
				e.printStackTrace();
		});
		thread.start();
	}
}
