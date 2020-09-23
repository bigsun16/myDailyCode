package com.qihui.sun.singleThreadedExecution;

//单线程执行模式
//共享资源
public class Gate {

	private int counter = 0;
	private String name = "noOne";
	private String address = "noWhere";

	//临界值
	public synchronized void pass(String name, String address) {
		counter++;
		//竞争
		this.name = name;
		this.address = address;
		verify();
	}

	private void verify() {
		if (name.charAt(0) != address.charAt(0)) {
			System.out.println("********BROKEN**********" + toString());
		}
	}

	@Override
	public synchronized String toString() {
		return "NO." + counter + ": " + name + "," + address;
	}
}
