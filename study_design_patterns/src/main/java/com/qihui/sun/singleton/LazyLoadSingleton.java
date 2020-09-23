package com.qihui.sun.singleton;

import java.io.Serializable;

/*
*没有线程安全问题，也实现了懒加载占不必要内存问题，但同样有可空指针异常隐患，
*比如这个类有很多引用，需要在构造方法内初始化，这时第一个调用者获取了实例，假设在还没有初始化完全时，该实例引用又被其它调用者获取，
*并调用实例成员，就造成了空指针异常.加个volatile关键字，并不能保证原子性，但可以保证可见性，一个线程修改该变量后会立刻从
*线程工作内存同步到主内存，另一个线程在读取该变量时一定会从主内存中取并保证它被初始化完全。
*加了volatile关键字的引用不被虚拟机优化，其实也不太好
*关于工作内存和主内存可简单理解为高速缓存（直接与CPU打交道）和主存（日常所说的内存条），注意工作内存是线程独享的，主存是线程共享的。
*/
public class LazyLoadSingleton implements Serializable{
//	解决序列化可能会破坏单例模式，比较每次反序列化一个序列化的对象实例时都会创建一个新的实例的问题
	private static final long serialVersionUID = 1L;
	private static volatile LazyLoadSingleton instance;
	private static volatile boolean flag = true;

//	flag可以防止反射创建对象
	private LazyLoadSingleton() {
		if (flag) {
			flag = false;
		} else {
			throw new RuntimeException("The instance  already exists ！");
		}
	};

	public static LazyLoadSingleton getInstance() {
		if (instance == null) {
			synchronized (LazyLoadSingleton.class) {
				if (instance == null) {
					instance = new LazyLoadSingleton();
				}
			}
		}
		return LazyLoadSingleton.instance;
	}
}
