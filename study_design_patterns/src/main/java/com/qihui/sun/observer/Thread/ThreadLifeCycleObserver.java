package com.qihui.sun.observer.Thread;

import java.util.List;

import com.qihui.sun.observer.Thread.ObserverRunnable.RunnableEvent;

public class ThreadLifeCycleObserver implements LifeCycleListener {

	private final Object LOCK = new Object();

	public void concurrentQuery(List<String> ids) {
		if (ids == null || ids.isEmpty()) {
			return;
		}
		for (String id : ids) {
			new Thread(new ObserverRunnable(this) {
				@Override
				public void run() {
					try {
						notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
						Thread.sleep(1000L);
//						int i = 1/0;
						notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
					} catch (Exception e) {
						notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
					}
				}
			}, id).start();
		}
	}

	@Override
	public void onEvent(RunnableEvent event) {
		synchronized (LOCK) {
			System.out.println("The Runnable [" + event.getThread().getName() + "],data changed and state is "+event.getState());
			if (event.getError() != null) {
				System.out.println("The Runnable [" + event.getThread().getName() + "] process failed!");
				event.getError().printStackTrace();
			}
		}
	}

}
