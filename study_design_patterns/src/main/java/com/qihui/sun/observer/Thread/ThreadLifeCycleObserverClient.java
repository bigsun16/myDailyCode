package com.qihui.sun.observer.Thread;

import java.util.Arrays;

public class ThreadLifeCycleObserverClient {
	public static void main(String[] args) {
		new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1", "2"));
	}
}
