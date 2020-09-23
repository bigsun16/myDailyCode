package com.qihui.sun.observer.Thread;

public class ObserverRunnable implements Runnable {
	private LifeCycleListener listener;

	public ObserverRunnable(LifeCycleListener listener) {
		this.listener = listener;
	}

	protected void notifyChange(final RunnableEvent event) {
		listener.onEvent(event);
	}

	@Override
	public void run() {

	}

	public enum RunnableState {
		RUNNING, ERROR, DONE
	}

	public static class RunnableEvent {
		private final RunnableState state;
		private final Thread thread;
		private final Throwable error;

		public RunnableEvent(RunnableState state, Thread thread, Throwable error) {
			super();
			this.state = state;
			this.thread = thread;
			this.error = error;
		}

		public RunnableState getState() {
			return state;
		}

		public Thread getThread() {
			return thread;
		}

		public Throwable getError() {
			return error;
		}

	}
}
