package com.qihui.sun.singleThreadedExecution;

public class User extends Thread {
	private final String myName;
	private final String myAddress;
	private Gate gate;

	public User(String myName, String myAddress, Gate gate) {
		super();
		this.myName = myName;
		this.myAddress = myAddress;
		this.gate = gate;
	}

	@Override
	public void run() {
		System.out.println(myName + " BEGIN");
		while (true) {
			this.gate.pass(myName, myAddress);
		}
	}

}
