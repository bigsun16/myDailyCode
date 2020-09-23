package com.qihui.sun.observer.Thread;

import com.qihui.sun.observer.Thread.ObserverRunnable.RunnableEvent;

public interface LifeCycleListener {

	public void onEvent(RunnableEvent event);

}
