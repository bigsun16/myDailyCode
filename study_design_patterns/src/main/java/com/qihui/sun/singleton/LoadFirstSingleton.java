package com.qihui.sun.singleton;

//关于单例，我们总是应该记住：线程安全，延迟加载，序列化与反序列化安全，反射安全是很重重要的。
//沒有线程安全问题，但是会造成对象生命周期过长，可能没用到，却占了一定内存
public class LoadFirstSingleton {

	private static LoadFirstSingleton instance = new LoadFirstSingleton();
	private LoadFirstSingleton() {}
	
	public static LoadFirstSingleton getInstance() {
		return LoadFirstSingleton.instance;
	}
}
