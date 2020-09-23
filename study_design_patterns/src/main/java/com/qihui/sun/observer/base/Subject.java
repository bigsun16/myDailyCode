package com.qihui.sun.observer.base;

import java.util.ArrayList;
import java.util.List;

public class Subject {

	List<Observer> observers = new ArrayList<>();
	private Integer state;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		if (this.state == state) {
			return;
		}
		this.state = state;
		notifyAllObserver();
	}

	public void attach(Observer observer) {
		observers.add(observer);
	}

	private void notifyAllObserver() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}
