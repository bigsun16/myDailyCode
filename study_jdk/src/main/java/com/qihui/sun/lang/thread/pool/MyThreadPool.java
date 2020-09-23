package com.qihui.sun.lang.thread.pool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyThreadPool extends Thread {
	private int size;
	private final int queueSize;
	private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;

	private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
	private static final LinkedList<Runnable> TASK_QUEUE_NOT_RUNNING = new LinkedList<>();
	private static volatile int seq = 0;
	private static final String THREAD_PREFIX = "MY_THREAD_POOL-";
	private static final ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");
	private static final List<WorkTask> THREAD_QUEUE = new ArrayList<>();
	private volatile boolean destroyFlag = false;
	private final RefusePolicy refusePolicy;
	public static final RefusePolicy DEFAULT_DISCARD_POLICY = () -> {
		throw new RefuseException("refuse this task.");
	};
	private int max;
	private int min;
	private int active;

	public MyThreadPool() {
		this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
	}

	public MyThreadPool(int min, int active, int max, int queueSize, RefusePolicy refusePolicy) {
		this.min = min;
		this.active = active;
		this.max = max;
		this.queueSize = queueSize;
		this.refusePolicy = refusePolicy;
		init();
	}

	private void init() {
		for (int i = 0; i < this.min; i++) {
			createWorkTask();
		}
		this.size = this.min;
		this.start();
	}

	public void submit(Runnable runnable) {
		if (destroyFlag) {
			throw new IllegalStateException("The thread pool has already destroyed and not allowed to submit task.");
		}

		synchronized (TASK_QUEUE) {
			if (TASK_QUEUE.size() >= queueSize) {
				refusePolicy.refuse();
			}
			TASK_QUEUE.addLast(runnable);
			TASK_QUEUE_NOT_RUNNING.addLast(runnable);
			TASK_QUEUE.notifyAll();
		}
	}

	@Override
	public void run() {
		while (!destroyFlag) {
			if (TASK_QUEUE.size() >= this.active && this.size < this.active) {
				System.out.printf("The pool increment to active...Pool default #Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d,ThreadQueueSize:%d\n", this.min, this.active, this.max,
						this.size, TASK_QUEUE.size(),THREAD_QUEUE.size());
				for (int i = this.size; i < this.active; i++) {
					createWorkTask();
				}
				this.size = this.active;
			} else if (TASK_QUEUE.size() >= this.max && this.size < this.max) {
				System.out.printf("The pool increment to max...Pool default #Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d,ThreadQueueSize:%d\n", this.min, this.active, this.max,
						this.size, TASK_QUEUE.size(),THREAD_QUEUE.size());
				for (int i = this.size; i < this.max; i++) {
					createWorkTask();
				}
				this.size = this.max;
			}
			synchronized (TASK_QUEUE) {
				if (TASK_QUEUE.isEmpty() && this.size > this.active) {
					System.out.printf("The pool increment to Reduce...Pool default #Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d,ThreadQueueSize:%d\n", this.min, this.active, this.max,
							this.size, TASK_QUEUE.size(),THREAD_QUEUE.size());
					int releaseSize = this.size - this.active;
					Iterator<WorkTask> iterator = THREAD_QUEUE.iterator();
					while (iterator.hasNext()) {
						if (releaseSize <= 0) {
							break;
						}
						WorkTask task = iterator.next();
						task.interrupt();
						task.close();
						iterator.remove();
						releaseSize--;
						System.out.println("Thread queue remove one ...");
					}
					this.size = this.active;
					System.out.println("THREAD_QUEUE size: " + THREAD_QUEUE.size());
				}
			}
		}
	}

	private void createWorkTask() {
		WorkTask task = new WorkTask(GROUP, THREAD_PREFIX + (seq++));
		task.start();
		THREAD_QUEUE.add(task);
	}

	public void shutdown() throws InterruptedException {
		while (!TASK_QUEUE.isEmpty()) {
			Thread.sleep(50);
		}
		synchronized (TASK_QUEUE) {
			int initValue = THREAD_QUEUE.size();
			while (initValue > 0) {
				for (WorkTask task : THREAD_QUEUE) {
					if (task.taskState == TaskState.BLOCK) {
						System.out.println("Thread pool shutDown!");
						task.interrupt();
						task.close();
						initValue--;
					}
				}
			}
			System.out.println("GROUP.activeCount():" + GROUP.activeCount());
			this.destroyFlag = true;
			System.out.println("The threadPool disposed!");
		}
	}

	public int getSize() {
		return this.size;
	}

	public int getQueueSize() {
		return queueSize;
	}

	public boolean isDestroy() {
		return this.destroyFlag;
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}


	public int getActive() {
		return active;
	}

	private enum TaskState {
		FREE, RUNNING, BLOCK, DEAD
	}

	public static class RefuseException extends RuntimeException {
		private static final long serialVersionUID = -5669376110455201273L;

		public RefuseException(String message) {
			super(message);
		}
	}

	public interface RefusePolicy {
		void refuse() throws RefuseException;
	}

	private static class WorkTask extends Thread {
		private volatile TaskState taskState = TaskState.FREE;

		public WorkTask(ThreadGroup group, String name) {
			super(group, name);
		}

		public TaskState geTaskState() {
			return this.taskState;
		}

		@Override
		public void run() {
			OUTER:
			while (this.taskState != TaskState.DEAD) {
				Runnable runnable;
				synchronized (TASK_QUEUE) {
					while (TASK_QUEUE_NOT_RUNNING.isEmpty()) {
						try {
							this.taskState = TaskState.BLOCK;
							TASK_QUEUE.wait();
						} catch (InterruptedException e) {
							System.out.println("Thread "+this.getName()+" closed!");
							break OUTER;
						}
					}
					runnable = TASK_QUEUE_NOT_RUNNING.removeFirst();
					this.taskState = TaskState.RUNNING;
				}
				runnable.run();
				synchronized (TASK_QUEUE){
					TASK_QUEUE.remove(runnable);
					this.taskState = TaskState.FREE;
					System.out.println("The runnable be serviced by " + Thread.currentThread().getName() + " stop！taskQueueSize: "+TASK_QUEUE.size());
				}
			}
		}

		public void close() {
			this.taskState = TaskState.DEAD;
		}
	}

	public static void main(String[] args) throws InterruptedException {
//		MyThreadPool pool = new MyThreadPool();
		MyThreadPool pool = new MyThreadPool(5,10,15,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
		for (int i = 0; i < 3; i++) {
			System.out.println(i);
			Thread.sleep(1000);
		}
		System.out.printf("Pool default #Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d,ThreadQueueSize:%d\n", pool.getMin(), pool.getActive(), pool.getMax(),
				pool.getSize(), pool.getQueueSize(),THREAD_QUEUE.size());
		for (int i = 0; i < 40; i++) {
			pool.submit(() -> {
				System.out.println("The runnable be serviced by " + Thread.currentThread().getName() + " start！");
				try {
					Thread.sleep(5_000);
				} catch (InterruptedException e) {
					System.out.println("InterruptedException.......");
				}
//				System.out.println("The runnable be serviced by " + Thread.currentThread().getName() + " stop！");
			});
		}

		Thread.sleep(5_000);
		pool.shutdown();
	}
}
