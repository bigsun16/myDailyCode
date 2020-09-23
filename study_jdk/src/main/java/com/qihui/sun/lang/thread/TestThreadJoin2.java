package com.qihui.sun.lang.thread;
//调用线程的join方法，表示在当前线程结束后再执行join代码之后代码
public class TestThreadJoin2 {
	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		System.out.println("start Time:"+startTime);
		Thread t1 = new Thread(new JoinRunnable("t1",100L));
		Thread t2 = new Thread(new JoinRunnable("t2",100L));
		Thread t3 = new Thread(new JoinRunnable("t3",100L));
		t1.start();
		t1.join();
		t2.start();
		t3.start();
		t2.join();
		t3.join();
		long endTime = System.currentTimeMillis();
		System.out.println("end Time:"+endTime);
		System.out.println("total Time:"+(endTime-startTime));
		
	}
}
class JoinRunnable implements Runnable{

	private String taskName;
	private Long spendTime;
	
	public JoinRunnable(String taskName, Long spendTime) {
		super();
		this.taskName = taskName;
		this.spendTime = spendTime;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(spendTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(taskName +"task spendTime: "+spendTime);
	}
}