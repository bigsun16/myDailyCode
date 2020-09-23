package com.qihui.sun.observer.base;
//观察者设计模式
public class ObserverClient {

	public static void main(String[] args) {
		final Subject subject = new Subject();
		new BinaryObserver(subject);
		new OctalObserver(subject);
		System.out.println("========================");
		subject.setState(10);
		System.out.println("========================");
		subject.setState(10);
		System.out.println("========================");
		subject.setState(15);
	}
}
