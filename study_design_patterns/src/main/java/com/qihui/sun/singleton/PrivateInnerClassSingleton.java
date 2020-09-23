package com.qihui.sun.singleton;

public class PrivateInnerClassSingleton {
	private PrivateInnerClassSingleton() {
	}

	private static class InstanceHolder {
		public final static PrivateInnerClassSingleton instance = new PrivateInnerClassSingleton();
	}

	public static PrivateInnerClassSingleton GetInstance() {
		return InstanceHolder.instance;
	}
}
